# Spring Microservices in Action

Overview of all projects from the "Spring Microservices in Action" by John Carnell, for the first edition of the book published in 2019.

## Main project

### Description

The main project represents a licensing application.

### The microservices

#### Developed microservices

[Eureka Service](./sma-eureka-service) <br/>
[Config Service](./sma-configuration-service) (repository used: [Git repo](./sma-config-repo)) <br/>
[Gateway Service](./sma-gateway-service) <br/>
[Special Route Service](./sma-special-routes-service) <br/>
[Licensing Service](./sma-licensing-service) <br/>
[Organization Service](./sma-organization-service) <br/>
[New Organization Service](./sma-organization-new-service) <br/>

#### Gateway development

The Gateway project was developed with Zuul. Currently, Spring Cloud Zuul is in maintenance and Spring Cloud Gateway is 
the preffered way to develop the proxy. For an implementation of Spring Cloud Gateway check [this](https://github.com/mariamihai/udemy-sbm-brewery-gateway) project.

A new application was developed to add potential special routes, that will route traffic to microservices different 
than the ones already defined under the gateway project. This project is associated with a dynamic route filter on the 
gateway service, which checks the possibility of adding intelligence to the routing of the request.

For A/B Testing, a new Organization Service was added. In a real project, the differences between the initial and the 
updated project might be important, but for the purpose of the current project, the only difference between the two is 
a "NEW::" String prefixing the name of the organization returned by each of the services.

#### Default port mapping - for single host

| Service Name | Port | 
| --------| -----|
| [Eureka Service](./sma-eureka-service) | 8761 |
| [Config Service](./sma-configuration-service) | 8888 |
| [Gateway Service](./sma-gateway-service) | 5555 |
| [Special Route Service](./sma-special-routes-service) | 8070 |
| [Licensing Service](./sma-licensing-service) | 8080 |
| [Organization Service](./sma-organization-service) | 8090 |
| [New Organization Service](./sma-organization-new-service) | 8099 |

#### Docker images

Automatic building was implemented for the microservices associated with this project.

For simplicity, I am using the build numbers provided by Travis CI as the version number for each different image constructed.

## Status

**[COMPLETED]** - I am setting a personal status of "Completed" and will probably not update this repository in the near 
future as this was a learning project.
