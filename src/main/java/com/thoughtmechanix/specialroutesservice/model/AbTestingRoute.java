package com.thoughtmechanix.specialroutesservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AbTestingRoute {

    @Id
    @Column(nullable = false)
    String serviceName;

    @Column(nullable = false)
    String active;

    @Column(nullable = false)
    String endpoint;

    @Column(nullable = false)
    Integer weight;
}
