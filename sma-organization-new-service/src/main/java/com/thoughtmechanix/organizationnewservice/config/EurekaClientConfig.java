package com.thoughtmechanix.organizationnewservice.config;

import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile({"local", "local-docker"})
@EnableEurekaClient
@Configuration
public class EurekaClientConfig {
}
