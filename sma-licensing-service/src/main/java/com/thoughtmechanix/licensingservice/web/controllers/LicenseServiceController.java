package com.thoughtmechanix.licensingservice.web.controllers;

import com.thoughtmechanix.licensingservice.web.model.License;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(value = "/v1/organizations/{organizationId}/licenses")
public class LicenseServiceController {

    @GetMapping(value = "/{licenseId}")
    public License getLicenses(@PathVariable("organizationId") UUID organizationId,
                               @PathVariable("licenseId") UUID licenseId) {
        return License.builder()
                .id(licenseId)
                .productName("License Name")
                .type("License type")
                .organizationId(organizationId)
                .build();
    }

}
