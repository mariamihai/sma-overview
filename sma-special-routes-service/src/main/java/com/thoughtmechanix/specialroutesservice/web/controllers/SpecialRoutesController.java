package com.thoughtmechanix.specialroutesservice.web.controllers;

import com.thoughtmechanix.specialroutesservice.services.AbTestingRouteService;
import com.thoughtmechanix.specialroutesservice.web.model.AbTestingRouteDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/route")
@RequiredArgsConstructor
public class SpecialRoutesController {

    private final AbTestingRouteService routeService;

    @GetMapping(value="/abtesting/{serviceName}")
    public AbTestingRouteDto abtesting(@PathVariable("serviceName") String serviceName) {
        return routeService.getRoute(serviceName);
    }
}
