package com.thoughtmechanix.licensingservice.services.organization;

import com.thoughtmechanix.licensingservice.web.model.OrganizationDto;

import java.util.UUID;

public interface OrganizationService {

    OrganizationDto getOrganization(UUID organizationId);
}
