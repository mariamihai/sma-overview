package com.thoughtmechanix.licensingservice.web.model;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class License {

    private UUID id;
    private String productName;
    private String type;
    private UUID organizationId;
}
