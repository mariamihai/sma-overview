package com.thoughtmechanix.licensingservice.services;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.thoughtmechanix.licensingservice.config.ServiceConfig;
import com.thoughtmechanix.licensingservice.domain.License;
import com.thoughtmechanix.licensingservice.exceptions.NotFoundException;
import com.thoughtmechanix.licensingservice.repositories.LicenseRepository;
import com.thoughtmechanix.licensingservice.services.organization.OrganizationServiceFeignImpl;
import com.thoughtmechanix.licensingservice.services.organization.OrganizationServiceRibbonAwareImpl;
import com.thoughtmechanix.licensingservice.services.organization.OrganizationServiceServiceDiscoveryImpl;
import com.thoughtmechanix.licensingservice.web.mappers.LicenseMapper;
import com.thoughtmechanix.licensingservice.web.model.LicenseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
// Adding default parameters for Hystrix for the service class
//@DefaultProperties(
//    commandProperties = {
//        @HystrixProperty(
//            name = "execution.isolation.thread.timeoutInMilliseconds",
//            value = "12000"
//        )
//    }
//)
public class LicenseServiceImpl implements LicenseService {


    private final LicenseRepository repository;
    private final LicenseMapper licenseMapper;

    private final ServiceConfig serviceConfig;

    // Wiring all OrganizationService implementations and have available all the implementations by clientType
    private final OrganizationServiceServiceDiscoveryImpl serviceDiscovery;
    private final OrganizationServiceRibbonAwareImpl ribbonAware;
    private final OrganizationServiceFeignImpl feign;


    @Override
    public LicenseDto getLicense(UUID organizationId, UUID licenseId, String clientType) {
        License license = repository.findByOrganizationIdAndLicenseId(organizationId, licenseId);

        validateLicense(license);

        license.setComment(serviceConfig.getExampleProperty());

        LicenseDto licenseDto = licenseMapper.licenseToDto(license);

        if("discovery".equals(clientType)) {
            log.debug("Using the ServiceDiscovery method of getting the organization data.");
            licenseDto.setOrganizationDto(serviceDiscovery.getOrganization(organizationId));
        }
        if("ribbon".equals(clientType)) {
            log.debug("Using the Ribbon-aware RestTemplate for getting the organization data.");
            licenseDto.setOrganizationDto(ribbonAware.getOrganization(organizationId));
        }
        if("feign".equals(clientType)) {
            log.debug("Using Feign client for getting the organization data.");
            licenseDto.setOrganizationDto(feign.getOrganization(organizationId));
        }

        return licenseDto;
    }

    @Override
    // Use Hystrix circuit breaker to wrap the calls to the db
    // Use custom timeout
//    @HystrixCommand(
//        commandProperties = {
//                @HystrixProperty(
//                    name = "execution.isolation.thread.timeoutInMilliseconds",
//                    value = "12000"
//                )
//            }
//    )
    // Use fallback method
//    @HystrixCommand(fallbackMethod = "buildFallbackLicenseList")
    // Implement the bulkhead pattern
    @HystrixCommand(
        fallbackMethod = "buildFallbackLicenseList",
        threadPoolKey = "licenseByOrgThreadPool",
        threadPoolProperties = {
            @HystrixProperty(name = "coreSize", value = "30"),
            @HystrixProperty(name = "maxQueueSize", value = "10")
        },
        // Adding custom circuit breaker behavior
        commandProperties = {
            // the minimum of consecutive calls that must occur within the window
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
            // the percentage of calls that must fail
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "75"),
            // the amount of time Hystrix sleeps before checking to see if the service is healthy again
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "7000"),
            // window size (default is 10 seconds - 10000 milliseconds)
            @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "15000"),
            // the number of times statistics are collected in the defined window
            @HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "5")
        }
    )
    public List<LicenseDto> getLicensesByOrg(UUID organizationId) {
//        simulateDatabaseRunningSlowly();

        List<License> licenses = repository.findByOrganizationId(organizationId);
        licenses.forEach(license -> license.setComment(serviceConfig.getExampleProperty()));

        return licenses.stream()
                .map(licenseMapper::licenseToDto)
                .collect(Collectors.toList());
    }

    private List<LicenseDto> buildFallbackLicenseList(UUID organizationId) {
        List<LicenseDto> fallbackList = new ArrayList<>();

        LicenseDto dto = LicenseDto.builder()
                .licenseId(UUID.fromString("00000000-0000-0000-0000-000000000000"))
                .organizationId(organizationId)
                .productName("Sorry, no licensing information currently available")
                .build();
        fallbackList.add(dto);

        return fallbackList;
    }

    @Override
    public void saveLicense(LicenseDto dto) {
        License license = licenseMapper.dtoToLicense(dto);
        license.setLicenseId(null);

        repository.save(license);
    }

    protected void validateLicense(License license) {
        if(license == null) throw new NotFoundException("License doesn't exist.");
    }

    protected void simulateDatabaseRunningSlowly() {
        Random random = new Random();
        // 50% chances of getting a slow database query
        int randomInt = random.nextInt(3);

        if(randomInt == 2) {
            sleepTenSeconds();
        }
    }

    protected void sleepTenSeconds() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
