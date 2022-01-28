package com.thoughtmechanix.licensingservice.repositories;

import com.thoughtmechanix.licensingservice.domain.License;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface LicenseRepository extends CrudRepository<License, UUID> {

    List<License> findByOrganizationId(UUID organizationId);
    License findByOrganizationIdAndLicenseId(UUID organizationId, UUID licenseId);
}
