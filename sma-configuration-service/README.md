[![Build Status](https://travis-ci.org/mariamihai/sma-configuration-service.svg?branch=master)](https://travis-ci.org/mariamihai/sma-configuration-service)

# Spring Microservices in Action - Configuration Service
The project represents my implementation based on the "Spring Microservices in Action" book.

## API Version
Currently the application is at _v1_.

## Environment properties
- server.port is set as environment properties to force setting of port in docker-compose (otherwise defaults to 8888)

## Implementation
### Active profiles
- native when using own configuration
- git when using the Git config-repo
- local when running on local environment


## Docker Image
Project Docker Image [here](https://hub.docker.com/repository/docker/mariamihai/sma-configuration-service).

### Articles
- [here](https://medium.com/@athulravindran/spring-cloud-config-server-discovery-first-vs-config-first-72cc6a56f471)
- [Docker article](https://www.freecodecamp.org/news/how-to-get-a-docker-container-ip-address-explained-with-examples/)