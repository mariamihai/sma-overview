[![Build Status](https://travis-ci.org/mariamihai/sma-configuration-service.svg?branch=master)](https://travis-ci.org/mariamihai/sma-configuration-service)
[![Docker](https://img.shields.io/docker/v/mariamihai/sma-configuration-service?sort=date)](https://hub.docker.com/r/mariamihai/sma-configuration-service)

# Spring Microservices in Action - Configuration Service
Spring Boot Microservice project.

  - [Description](#description)
  - [Docker images](#docker-images)
  - [Implementation details](#implementation-details)
    - [Properties](#properties)
    - [Profiles](#profiles)

## Description
The project represents my implementation based on the "Spring Microservices in Action" book.

The project provides externalized configuration for the principal services: [Licensing Service](https://github.com/mariamihai/sma-licensing-service), [Organization Service](https://github.com/mariamihai/sma-organization-service) and [New Organization Service](https://github.com/mariamihai/sma-organization-new-service). 
It uses a [Git repository](https://github.com/mariamihai/sma-config-repo) for the properties files.

An overview of all the projects involved can be found [here](https://github.com/mariamihai/sma-overview).

## Docker images
Automatic building was implemented for the microservices associated with this project.
For simplicity, I am using the build numbers provided by Travis CI as the version number for each different image constructed.

## Implementation details
### Properties
- the name of the application, used by the other services 
```
spring.application.name=config-service
```
- application server port (default value)
```
server.port=8888
```

### Profiles
- `native` when using own configuration
- `git` when using the Git config-repo
- `local` when running on local environment
