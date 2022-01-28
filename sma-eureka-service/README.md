[![Build Status](https://travis-ci.org/mariamihai/sma-eureka-service.svg?branch=master)](https://travis-ci.org/mariamihai/sma-eureka-service)
[![Docker](https://img.shields.io/docker/v/mariamihai/sma-eureka-service?sort=date)](https://hub.docker.com/r/mariamihai/sma-eureka-service)

# Spring Microservices in Action - Eureka Service

Spring Boot Microservice project.

  - [Description](#description)
  - [Docker images](#docker-images)
  - [Implementation details](#implementation-details)
    - [Properties](#properties)
    - [Profiles](#profiles)

## Description

The project represents my implementation based on the "Spring Microservices in Action" book.

The project provides service discovery and it is used by all other applications for registration and communication between themselves.

An overview of all the projects involved can be found [here](../../..).

## Docker images

Automatic building was implemented for the microservices associated with this project.

For simplicity, I am using the build numbers provided by Travis CI as the version number for each different image constructed.

## Implementation details

### Properties

- the name of the application, used by the other services 
```
spring.application.name=eureka
```
- application server port (default value)
```
server.port=8761
```

### Profiles

There is no active profile defined for the current project.
