package com.thoughtmechanix.specialroutesservice.services;

import com.thoughtmechanix.specialroutesservice.exceptions.NoRouteFound;
import com.thoughtmechanix.specialroutesservice.model.AbTestingRoute;
import com.thoughtmechanix.specialroutesservice.repositories.AbTestingRouteRepository;
import com.thoughtmechanix.specialroutesservice.web.mappers.AbTestingRouteMapper;
import com.thoughtmechanix.specialroutesservice.web.model.AbTestingRouteDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AbTestingRouteServiceImpl implements AbTestingRouteService {

    private final AbTestingRouteRepository repository;
    private final AbTestingRouteMapper mapper;

    @Override
    public AbTestingRouteDto getRoute(String serviceName) {
        return mapper.toDto(validRoute(serviceName));
    }

    @Override
    public void saveAbTestingRoute(AbTestingRouteDto dto) {
        repository.save(mapper.fromDto(dto));
    }

    @Override
    public void updateRouteAbTestingRoute(AbTestingRouteDto dto) {
        repository.save(mapper.fromDto(dto));
    }

    @Override
    public void deleteRoute(AbTestingRouteDto dto) {
        validRoute(dto.getServiceName());

        repository.deleteById(dto.getServiceName());
    }

    private AbTestingRoute validRoute(String serviceName) {
        AbTestingRoute route = repository.findByServiceName(serviceName);

        if(route == null) {
            throw new NoRouteFound();
        }

        return route;
    }
}
