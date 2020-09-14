[![Build Status](https://travis-ci.org/mariamihai/sma-special-routes-service.svg?branch=master)](https://travis-ci.org/mariamihai/sma-special-routes-service)
[![Docker](https://img.shields.io/docker/v/mariamihai/sma-special-routes-service?sort=date)](https://hub.docker.com/r/mariamihai/sma-special-routes-service)

# Spring Microservices in Action - Special Routes Service
Spring Boot Microservice project.

  - [Description](#description)
  - [Docker images](#docker-images)
  - [Implementation details](#implementation-details)
    - [Properties](#properties)
    - [Profiles](#profiles)

## Description
The project represents my implementation based on the "Spring Microservices in Action" book.

The service is used to provide alternative routes for known mapping routes in the [Gateway Service](https://github.com/mariamihai/sma-gateway-service).  

An overview of all the projects involved can be found [here](https://github.com/mariamihai/sma-overview).

## Docker images
Automatic building was implemented for the microservices associated with this project.
For simplicity, I am using the build numbers provided by Travis CI as the version number for each different image constructed.

## Implementation details
### Properties
- the name of the application, used by the other services 
```
spring.application.name=special-routes-service
```
- application server port (added as environment variable)
```
server.port=8070
```
- adding the secret String for encryption as an environment variable
```
ENCRYPT_KEY=MySuperExtremelySecretKey
```

### Profiles
There is no active profile defined for the current project.
