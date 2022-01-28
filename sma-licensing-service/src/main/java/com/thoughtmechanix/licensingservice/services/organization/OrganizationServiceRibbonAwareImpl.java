package com.thoughtmechanix.licensingservice.services.organization;

import com.thoughtmechanix.licensingservice.web.model.OrganizationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
public class OrganizationServiceRibbonAwareImpl implements OrganizationService {

    private RestTemplate restTemplate;

    @Override
    public OrganizationDto getOrganization(UUID organizationId) {
        ResponseEntity<OrganizationDto> restExchange =
                restTemplate.exchange("http://organization-service/v1/organizations/{organizationId}",
                                      HttpMethod.GET, null, OrganizationDto.class, organizationId);

        return restExchange.getBody();
    }

    @Qualifier("ribbonAwareRestTemplate")
    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
}
