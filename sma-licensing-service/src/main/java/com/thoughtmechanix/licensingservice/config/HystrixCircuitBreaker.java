package com.thoughtmechanix.licensingservice.config;

import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.context.annotation.Configuration;

@EnableCircuitBreaker
@Configuration
public class HystrixCircuitBreaker {
}
