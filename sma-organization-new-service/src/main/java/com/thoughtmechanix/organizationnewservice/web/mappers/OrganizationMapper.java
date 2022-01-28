package com.thoughtmechanix.organizationnewservice.web.mappers;

import com.thoughtmechanix.organizationnewservice.domain.Organization;
import com.thoughtmechanix.organizationnewservice.web.model.OrganizationDto;
import org.mapstruct.Mapper;

@Mapper
public interface OrganizationMapper {

    Organization dtoToOrganization(OrganizationDto dto);
    OrganizationDto organizationToDto(Organization organization);
}
