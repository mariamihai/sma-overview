package com.thoughtmechanix.organizationservice.services;

import com.thoughtmechanix.organizationservice.web.model.OrganizationDto;

import java.util.List;
import java.util.UUID;

public interface OrganizationService {

    List<OrganizationDto> getAllOrganizations();
    OrganizationDto getOrganization(UUID id);
    OrganizationDto save(OrganizationDto organization);
    OrganizationDto update(UUID id, OrganizationDto organization);
    void delete(UUID id);
}
