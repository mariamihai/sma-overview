package com.thoughtmechanix.licensingservice.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LicenseDto {

    private UUID licenseId;

    private UUID organizationId;

    private String productName;
    private String licenseType;

    private String comment;
}
