package com.thoughtmechanix.organizationservice.services;

import com.thoughtmechanix.organizationservice.domain.Organization;
import com.thoughtmechanix.organizationservice.exceptions.NotFoundException;
import com.thoughtmechanix.organizationservice.repositories.OrganizationRepository;
import com.thoughtmechanix.organizationservice.web.mappers.OrganizationMapper;
import com.thoughtmechanix.organizationservice.web.model.OrganizationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationRepository repository;
    private final OrganizationMapper mapper;

    @Override
    public List<OrganizationDto> getAllOrganizations() {
        Iterable<Organization> all = repository.findAll();

        List<OrganizationDto> dtos = new ArrayList<>();
        all.forEach(entity -> dtos.add(mapper.organizationToDto(entity)));

        return dtos;
    }

    @Override
    public OrganizationDto getOrganization(UUID id) {
        Optional<Organization> organizationOptional = repository.findById(id);

        return mapper.organizationToDto(organizationOptional.orElseThrow(NotFoundException::new));
    }

    @Override
    public OrganizationDto save(OrganizationDto dto) {
        Organization organization = mapper.dtoToOrganization(dto);
        // TODO - uncomment when generating ids
//        organization.setId(null);

        return mapper.organizationToDto(repository.save(organization));
    }

    @Override
    public OrganizationDto update(UUID id, OrganizationDto dto) {
        Organization organization = validateOrganization(id);

        organization.setOrganizationName(dto.getOrganizationName());
        organization.setContactName(dto.getContactName());
        organization.setContactEmail(dto.getContactEmail());
        organization.setContactPhone(dto.getContactPhone());

        return mapper.organizationToDto(repository.save(organization));
    }

    @Override
    public void delete(UUID id) {
        validateOrganization(id);

        repository.deleteById(id);
    }

    protected Organization validateOrganization(UUID id) {
        return repository.findById(id).orElseThrow(NotFoundException::new);
    }
}
