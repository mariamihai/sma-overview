package com.thoughtmechanix.licensingservice.services.organization;

import com.thoughtmechanix.licensingservice.web.model.OrganizationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrganizationServiceFeignImpl implements OrganizationService {

    private final OrganizationServiceFeignClient feignClient;

    @Override
    public OrganizationDto getOrganization(UUID organizationId) {
        ResponseEntity<OrganizationDto> response = feignClient.getOrganization(organizationId);

        return response.getBody();
    }
}
