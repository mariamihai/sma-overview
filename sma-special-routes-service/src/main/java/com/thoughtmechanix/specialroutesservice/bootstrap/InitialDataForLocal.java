package com.thoughtmechanix.specialroutesservice.bootstrap;

import com.thoughtmechanix.specialroutesservice.model.AbTestingRoute;
import com.thoughtmechanix.specialroutesservice.repositories.AbTestingRouteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("local")
@Component
@RequiredArgsConstructor
public class InitialDataForLocal implements CommandLineRunner {

    private final AbTestingRouteRepository repository;


    @Override
    public void run(String... args) {
        if(isEmpty()) {
            addData();
        }
    }

    private void addData() {
        AbTestingRoute route = AbTestingRoute.builder()
                .serviceName("organization-service")
                .active("Y")
//                .endpoint("http://organization-new-service:8099")
                .endpoint("http://localhost:8099")
                .weight(5)
                .build();

        repository.save(route);
    }

    private boolean isEmpty() {
        return repository.count() == 0;
    }
}
