[![Build Status](https://travis-ci.org/mariamihai/sma-organization-new-service.svg?branch=master)](https://travis-ci.org/mariamihai/sma-organization-new-service)
[![Docker](https://img.shields.io/docker/v/mariamihai/sma-organization-new-service?sort=date)](https://hub.docker.com/r/mariamihai/sma-organization-new-service)

# Spring Microservices in Action - New Organization Service
Spring Boot Microservice project.

## Description
The project represents my implementation based on the "Spring Microservices in Action" book.

The service represents an alternative to the initial [Organization Service](https://github.com/mariamihai/sma-organization-service).
A dynamic route filter was added in the [Gateway Service](https://github.com/mariamihai/sma-gateway-service) to evaluate new requests. Based on a call to the 
[Special Route Service](https://github.com/mariamihai/sma-special-routes-service), the possibility of using of the initial Organization Service or the current project 
is evaluated.
In a real project, the differences between the initial and the updated project might be important, but for the purpose of the current project, the only difference between 
the two is a "NEW::" String prefixing the name of the organization returned by each of the services.

An overview of all the projects involved can be found [here](https://github.com/mariamihai/sma-overview).

## Docker images
Automatic building was implemented for the microservices associated with this project.
For simplicity, I am using the build numbers provided by Travis CI as the version number for each different image constructed.

## Implementation details
### Properties
- the name of the application, used by the other services 
```
spring.application.name=organization-new-service
```
- application server port
```
server.port=8099
```
- adding the secret String for encryption as an environment variable
```
ENCRYPT_KEY=MySuperExtremelySecretKey
```

### Profiles
Profiles active: `local`.
