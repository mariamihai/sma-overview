package com.thoughtmechanix.organizationservice.web.controller;

import com.thoughtmechanix.organizationservice.domain.Organization;
import com.thoughtmechanix.organizationservice.services.OrganizationService;
import com.thoughtmechanix.organizationservice.web.model.OrganizationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value="v1/organizations")
@RequiredArgsConstructor
public class OrganizationController {

    private final OrganizationService organizationService;

    @GetMapping(value = "/{organizationId")
    public OrganizationDto getOrganization(@PathVariable("organizationId") UUID id) {
        return organizationService.getOrganization(id);
    }

    @PostMapping()
    public OrganizationDto saveOrganization(@RequestBody OrganizationDto dto) {
        return organizationService.save(dto);
    }

    @PutMapping(value="/{organizationId}")
    public OrganizationDto updateOrganization(@PathVariable("organizationId") UUID id,
                                              @RequestBody OrganizationDto dto) {
        return organizationService.update(id, dto);
    }

    @DeleteMapping(value="/{organizationId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrganization(@PathVariable("organizationId") UUID id) {
        organizationService.delete(id);
    }
}
