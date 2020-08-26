package com.thoughtmechanix.gatewayservice.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AbTestingRouteDto {

    String serviceName;
    String active;
    String endpoint;
    Integer weight;
}
