package com.thoughtmechanix.licensingservice.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Getter
public class ServiceConfig {

    @Value("${example.property}")
    private String exampleProperty;
}
