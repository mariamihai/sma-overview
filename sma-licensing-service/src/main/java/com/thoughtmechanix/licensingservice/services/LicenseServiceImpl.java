package com.thoughtmechanix.licensingservice.services;

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

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
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
    public List<LicenseDto> getLicensesByOrg(UUID organizationId) {
        List<License> licenses = repository.findByOrganizationId(organizationId);
        licenses.forEach(license -> license.setComment(serviceConfig.getExampleProperty()));

        return licenses.stream()
                .map(licenseMapper::licenseToDto)
                .collect(Collectors.toList());
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
}
