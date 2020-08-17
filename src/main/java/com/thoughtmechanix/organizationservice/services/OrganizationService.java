package com.thoughtmechanix.organizationservice.services;

import com.thoughtmechanix.organizationservice.web.model.OrganizationDto;

import java.util.UUID;

public interface OrganizationService {

    OrganizationDto getOrganization(UUID id);
    OrganizationDto save(OrganizationDto organization);
    OrganizationDto update(UUID id, OrganizationDto organization);
    void delete(UUID id);
}
