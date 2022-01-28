package com.thoughtmechanix.organizationservice.web.mappers;

import com.thoughtmechanix.organizationservice.domain.Organization;
import com.thoughtmechanix.organizationservice.web.model.OrganizationDto;
import org.mapstruct.Mapper;

@Mapper
public interface OrganizationMapper {

    Organization dtoToOrganization(OrganizationDto dto);
    OrganizationDto organizationToDto(Organization organization);
}
