package com.thoughtmechanix.organizationnewservice.services;

import com.thoughtmechanix.organizationnewservice.domain.Organization;
import com.thoughtmechanix.organizationnewservice.exceptions.NotFoundException;
import com.thoughtmechanix.organizationnewservice.repositories.OrganizationRepository;
import com.thoughtmechanix.organizationnewservice.web.mappers.OrganizationMapper;
import com.thoughtmechanix.organizationnewservice.web.model.OrganizationDto;
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
        all.forEach(entity -> dtos.add(entityToNewDto(entity)));

        return dtos;
    }

    @Override
    public OrganizationDto getOrganization(UUID id) {
        Optional<Organization> organizationOptional = repository.findById(id);
        Organization entity = organizationOptional.orElseThrow(NotFoundException::new);

        return entityToNewDto(entity);
    }

    private OrganizationDto entityToNewDto(Organization entity) {
        entity.setOrganizationName("NEW::" + entity.getOrganizationName());
        return mapper.organizationToDto(entity);
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
