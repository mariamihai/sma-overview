package com.thoughtmechanix.licensingservice.web.controllers;

import com.thoughtmechanix.licensingservice.services.LicenseService;
import com.thoughtmechanix.licensingservice.web.model.LicenseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/v1/organizations/{organizationId}/licenses")
@RequiredArgsConstructor
public class LicenseServiceController {

    private final LicenseService licenseService;

    @GetMapping(value = "/{licenseId}")
    public LicenseDto getLicenseByOrg(@PathVariable("organizationId") UUID organizationId,
                                  @PathVariable("licenseId") UUID licenseId) {
        return licenseService.getLicense(organizationId, licenseId);
    }

    @GetMapping
    public List<LicenseDto> getLicenses(@PathVariable("organizationId") UUID organizationId) {
        return licenseService.getLicensesByOrg(organizationId);
    }
}
