package com.thoughtmechanix.licensingservice.services.organization;

import com.thoughtmechanix.licensingservice.exceptions.NotFoundException;
import com.thoughtmechanix.licensingservice.web.model.OrganizationDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@Service
public class OrganizationServiceServiceDiscoveryImpl implements OrganizationService {

    private final DiscoveryClient discoveryClient;
    private final RestTemplate restTemplate;


    public OrganizationServiceServiceDiscoveryImpl(DiscoveryClient discoveryClient, RestTemplateBuilder restTemplateBuilder) {
        this.discoveryClient = discoveryClient;
        this.restTemplate = restTemplateBuilder.build();
    }


    @Override
    public OrganizationDto getOrganization(UUID organizationId) {
        List<ServiceInstance> instances = discoveryClient.getInstances("organization-service");

        if(instances.isEmpty()) {
            throw new NotFoundException("Didn't find any organization service.");
        }

        String serviceUri = createUri(instances.get(0), organizationId);
        ResponseEntity<OrganizationDto> restExchange =
                restTemplate.exchange(serviceUri, HttpMethod.GET, null, OrganizationDto.class, organizationId);

        return restExchange.getBody();
    }

    private String createUri(ServiceInstance instance, UUID organizationId) {
        return String.format("%s/v1/organizations/%s", instance.getUri().toString(), organizationId);
    }
}
