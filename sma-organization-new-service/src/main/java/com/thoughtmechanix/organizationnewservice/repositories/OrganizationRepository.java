package com.thoughtmechanix.organizationnewservice.repositories;

import com.thoughtmechanix.organizationnewservice.domain.Organization;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface OrganizationRepository extends CrudRepository<Organization, UUID> {
}
