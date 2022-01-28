package com.thoughtmechanix.organizationservice.repositories;

import com.thoughtmechanix.organizationservice.domain.Organization;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface OrganizationRepository extends CrudRepository<Organization, UUID> {
}
