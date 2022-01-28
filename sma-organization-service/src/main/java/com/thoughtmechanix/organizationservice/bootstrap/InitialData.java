package com.thoughtmechanix.organizationservice.bootstrap;

import com.thoughtmechanix.organizationservice.domain.Organization;
import com.thoughtmechanix.organizationservice.repositories.OrganizationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class InitialData implements CommandLineRunner {

    private final OrganizationRepository organizationRepository;

    @Override
    public void run(String... args) {
        if(isEmptyRepository()) {
            addOrganizations();
        }
    }

    private void addOrganizations() {
        addOrganization(UUID.fromString("89aaf748-85d8-4d03-9ef7-e97f5647cbf6"), "customer-crm-co",
                "Mark Balster", "mark.balster@custcrmco.com", "823-555-1212");

        addOrganization(UUID.fromString("6f1b066a-17ee-45ca-8923-4f9538e7cde5"), "HR-PowerSuite",
                "Doug Drewry", "doug.drewry@hr.com", "920-555-1212");
    }

    private void addOrganization(UUID id, String organizationName, String contactName, String contactEmail, String contactPhone) {
        Organization organization = Organization.builder()
                .id(id)
                .organizationName(organizationName)
                .contactName(contactName)
                .contactEmail(contactEmail)
                .contactPhone(contactPhone)
                .build();

        organizationRepository.save(organization);
    }

    private boolean isEmptyRepository() {
        return organizationRepository.count() == 0;
    }
}
