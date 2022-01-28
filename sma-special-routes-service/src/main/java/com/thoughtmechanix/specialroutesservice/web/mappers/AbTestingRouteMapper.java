package com.thoughtmechanix.specialroutesservice.web.mappers;

import com.thoughtmechanix.specialroutesservice.model.AbTestingRoute;
import com.thoughtmechanix.specialroutesservice.web.model.AbTestingRouteDto;
import org.mapstruct.Mapper;

@Mapper
public interface AbTestingRouteMapper {

    AbTestingRoute fromDto(AbTestingRouteDto dto);
    AbTestingRouteDto toDto(AbTestingRoute route);
}
