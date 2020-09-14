[![Build Status](https://travis-ci.org/mariamihai/sma-gateway-service.svg?branch=master)](https://travis-ci.org/mariamihai/sma-gateway-service)
[![Docker](https://img.shields.io/docker/v/mariamihai/sma-eureka-service?sort=date)](https://hub.docker.com/r/mariamihai/sma-eureka-service)

# Spring Microservices in Action - Gateway Service
Spring Boot Microservice project.

## Description
The project represents my implementation based on the "Spring Microservices in Action" book.

The project represents the gateway service for the [Licensing Service](https://github.com/mariamihai/sma-licensing-service), [Organization Service](https://github.com/mariamihai/sma-organization-service) and [New Organization Service](https://github.com/mariamihai/sma-organization-new-service).

The current project was developed with Zuul. Currently, Spring Cloud Zuul is in maintenance and Spring Cloud Gateway is the prefered way to develop the proxy. For an implementation of Spring Cloud Gateway check [this](https://github.com/mariamihai/udemy-sbm-brewery-gateway) project.

An overview of all the projects involved can be found [here](https://github.com/mariamihai/sma-overview).

## Docker images
Automatic building was implemented for the microservices associated with this project.
For simplicity, I am using the build numbers provided by Travis CI as the version number for each different image constructed.

## Implementation details
### Development
A new application was developed to add potential special routes, that will route traffic to microservices different than the ones already defined under the gateway project.
[This project](https://github.com/mariamihai/sma-special-routes-service) is associated with a dynamic route filter on the gateway service, which checks the possibility of adding intelligence to the routing of the request.
For Ab Testing, a new Organization Service was added. In a real project, the differences between the initial and the updated project might be important, but for the purpose of the current project, the only difference between the two is a "NEW::" String prefixing the name of the organization returned by each of the services.

### Properties
- the name of the application, used by the other services 
```
spring.application.name=gateway-service
```
- application server port (default value)
```
server.port=5555
```

### Profiles
Active profile: `local`.
