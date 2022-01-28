package com.thoughtmechanix.specialroutesservice.services;

import com.thoughtmechanix.specialroutesservice.web.model.AbTestingRouteDto;

public interface AbTestingRouteService {

    AbTestingRouteDto getRoute(String serviceName);
    void saveAbTestingRoute(AbTestingRouteDto dto);
    void updateRouteAbTestingRoute(AbTestingRouteDto dto);
    void deleteRoute(AbTestingRouteDto dto);
}
