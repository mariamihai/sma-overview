package com.thoughtmechanix.organizationservice.web.controller;

import com.thoughtmechanix.organizationservice.services.OrganizationService;
import com.thoughtmechanix.organizationservice.web.model.OrganizationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value="v1/organizations")
@RequiredArgsConstructor
public class OrganizationController {

    private final OrganizationService organizationService;

    @GetMapping
    public List<OrganizationDto> getAllOrganizations() {
        return organizationService.getAllOrganizations();
    }

    @GetMapping("/{id}")
    public OrganizationDto getOrganization(@PathVariable("id") UUID id) {
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
