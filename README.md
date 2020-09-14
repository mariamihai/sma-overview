# Spring Microservices in Action
Overview of all projects from the "Spring Microservices in Action" by John Carnell.

The first edition of the book was published in 2019. The second edition is expected to be published in 2021.


## Main project
### Description
The main project represents a licensing application.

### The microservices
#### Developed microservices
[Eureka Service](https://github.com/mariamihai/sma-eureka-service)
[Config Service](https://github.com/mariamihai/sma-configuration-service) (repository used: [Git repo](https://github.com/mariamihai/sma-config-repo))
[Gateway Service](https://github.com/mariamihai/sma-gateway-service)
[Special Route Service](https://github.com/mariamihai/sma-special-routes-service)
[Licensing Service](https://github.com/mariamihai/sma-licensing-service)
[Organization Service](https://github.com/mariamihai/sma-organization-service)
[New Organization Service](https://github.com/mariamihai/sma-organization-new-service)

#### Gateway development
The Gateway project was developed with Zuul. Currently, Spring Cloud Zuul is in maintenance and Spring Cloud Gateway is the prefered way to develop the proxy. For an implementation of Spring Cloud Gateway check [this](https://github.com/mariamihai/udemy-sbm-brewery-gateway) project.

A new application was developed to add potential special routes, that will route traffic to microservices different than the ones already defined under the gateway project. This project is associated with a dynamic route filter on the gateway service, which checks the possibility of adding intelligence to the routing of the request.
For Ab Testing, a new Organization Service was added. In a real project, the differences between the initial and the updated project might be important, but for the purpose of the current project, the only difference between the two is a "NEW::" String prefixing the name of the organization returned by each of the services.

#### Additional applications needed
For the needed applications I've used Docker containers.

##### PostgreSQL

#### Default port mapping - for single host
| Service Name | Port | 
| --------| -----|
|||


#### Docker images
Automatic building was implemented for the microservices associated with this project.
For simplicity, I am using the build numbers provided by Travis CI as the version number for each different image constructed.
