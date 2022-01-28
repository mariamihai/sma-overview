package com.thoughtmechanix.licensingservice.bootstrap;

import com.thoughtmechanix.licensingservice.domain.License;
import com.thoughtmechanix.licensingservice.repositories.LicenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class InitialData implements CommandLineRunner {

    private final LicenseRepository licenseRepository;

    @Override
    public void run(String... args) {
        if(isEmptyRepository()) {
            addLicenses();
        }
    }

    private void addLicenses() {
        addLicense("89aaf748-85d8-4d03-9ef7-e97f5647cbf6", "user", "customer-crm-co", 100, 5);
        addLicense("89aaf748-85d8-4d03-9ef7-e97f5647cbf6", "user", "suitability-plus", 200, 189);
        addLicense("6f1b066a-17ee-45ca-8923-4f9538e7cde5", "user", "sHR-PowerSuite", 100, 4);
        addLicense("b08f92eb-0787-4aee-b317-a8b40fb7cd8a", "core-prod", "WildCat Application Gateway", 16, 16);
    }

    private void addLicense(String organizationIdString, String licenseType, String productName,
                            Integer licenseMax, Integer licenseAllocated) {
        License license = License.builder()
                .organizationId(UUID.fromString(organizationIdString))
                .licenseType(licenseType)
                .productName(productName)
                .licenseMax(licenseMax)
                .licenseAllocated(licenseAllocated)
                .build();
        licenseRepository.save(license);
    }

    private boolean isEmptyRepository() {
        return licenseRepository.count() == 0;
    }
}
