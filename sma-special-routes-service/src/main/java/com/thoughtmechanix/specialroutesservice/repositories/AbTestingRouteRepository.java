package com.thoughtmechanix.specialroutesservice.repositories;

import com.thoughtmechanix.specialroutesservice.model.AbTestingRoute;
import org.springframework.data.repository.CrudRepository;

public interface AbTestingRouteRepository extends CrudRepository<AbTestingRoute,String> {

    AbTestingRoute findByServiceName(String serviceName);
}
