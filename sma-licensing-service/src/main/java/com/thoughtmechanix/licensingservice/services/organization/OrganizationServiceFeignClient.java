package com.thoughtmechanix.licensingservice.services.organization;

import com.thoughtmechanix.licensingservice.web.model.OrganizationDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "organization-service")
public interface OrganizationServiceFeignClient {

    @GetMapping(value = "/v1/organizations/{organizationId}",
                consumes = "application/json")
    ResponseEntity<OrganizationDto> getOrganization(@PathVariable UUID organizationId);
}
