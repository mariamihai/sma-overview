package com.thoughtmechanix.organizationservice.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrganizationDto {

    UUID id;

    String organizationName;

    String contactName;
    String contactEmail;
    String contactPhone;
}
