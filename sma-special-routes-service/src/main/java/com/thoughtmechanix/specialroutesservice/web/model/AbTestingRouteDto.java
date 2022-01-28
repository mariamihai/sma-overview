package com.thoughtmechanix.specialroutesservice.web.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class AbTestingRouteDto {

    String serviceName;
    String active;
    String endpoint;
    Integer weight;
}
