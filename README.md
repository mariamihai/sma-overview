[![Build Status](https://travis-ci.org/mariamihai/sma-organization-service.svg?branch=master)](https://travis-ci.org/mariamihai/sma-organization-service)

# Spring Microservices in Action - Organization Service
The project represents my implementation based on the "Spring Microservices in Action" book.

## API Version
Currently the application is at _v1_.

## Environment properties
- server.port is set as environment properties to force setting of port in docker-compose (defaults to 8080)

## Run
Run with encryption key or from the docker-compose file (currently in the Configuration Service).
```
mvn spring-boot:run -Dspring-boot.run.arguments=--encrypt.key=MySuperExtremelySecretKey
```
### Running locally
Profile active = local
### Running with Docker
Profile active = local-docker

### API calls
TODO