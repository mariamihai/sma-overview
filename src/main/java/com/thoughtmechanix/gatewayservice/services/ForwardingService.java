package com.thoughtmechanix.gatewayservice.services;

import com.thoughtmechanix.gatewayservice.model.AbTestingRouteDto;

public interface ForwardingService {

    AbTestingRouteDto getAbRoutingInfo(String serviceName);
    boolean useSpecialRoute(AbTestingRouteDto testRoute);
    String buildRouteString(String oldEndpoint, String newEndpoint, String serviceName);
    void forwardToSpecialRoute(String route);
}
