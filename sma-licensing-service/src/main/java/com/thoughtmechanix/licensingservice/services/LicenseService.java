package com.thoughtmechanix.licensingservice.services;

import com.thoughtmechanix.licensingservice.web.model.LicenseDto;

import java.util.List;
import java.util.UUID;

public interface LicenseService {

    LicenseDto getLicense(UUID organizationId, UUID licenseId);
    List<LicenseDto> getLicensesByOrg(UUID organizationId);
    void saveLicense(LicenseDto dto);
}
