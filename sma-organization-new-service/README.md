[![Build Status](https://travis-ci.org/mariamihai/sma-organization-new-service.svg?branch=master)](https://travis-ci.org/mariamihai/sma-organization-new-service)
[![Docker](https://img.shields.io/docker/v/mariamihai/sma-organization-new-service?sort=date)](https://hub.docker.com/r/mariamihai/sma-organization-new-service)

# Spring Microservices in Action - New Organization Service

Spring Boot Microservice project.

  - [Description](#description)
  - [Docker images](#docker-images)
  - [Implementation details](#implementation-details)
    - [Properties](#properties)
    - [Profiles](#profiles)
  - [API calls](#api-calls)
    - [Obtain all organizations](#obtain-all-organizations)
    - [Obtain organization information](#obtain-organization-information)
    - [Save new organization](#save-new-organization)
    - [Update existing organization](#update-existing-organization)
    - [Delete Organization](#delete-organization)

## Description

The project represents my implementation based on the "Spring Microservices in Action" book.

The service represents an alternative to the initial [Organization Service](../sma-organization-service).

A dynamic route filter was added in the [Gateway Service](../sma-gateway-service) to evaluate new requests. 
Based on a call to the [Special Route Service](../sma-special-routes-service), the possibility of using of the 
initial Organization Service or the current project is evaluated.

In a real project, the differences between the initial and the updated project might be important, but for the purpose 
of the current project, the only difference between the two is a "NEW::" String prefixing the name of the organization 
returned by each of the services.

An overview of all the projects involved can be found [here](../../..).

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
encrypt.key=MySuperExtremelySecretKey
```

### Profiles

Profiles active: 
- `local` when running on local environment
- `local-docker` when running on Docker (not production ready environment)

## API calls

### Obtain all organizations

Obtaining the information associated with all existing organizations. This was added as a testing helper and probably 
should be removed in a production environment.

Pagination currently is not implemented.

 * __URI:__ _v1/organizations/_
 * __Method:__ _GET_

 * __URL params:__ <br/>
    * required: - <br/>
    * optional: - <br/>
    
 * __Query params:__ <br/>
    * required: - <br/>
    * optional: - <br/>
        
 * __Success response:__
    * Code: 200 OK <br/>
    * Content: TODO - will be added
    ```
    ```
 
### Obtain organization information

Obtain an organization based on its organization id.

 * __URI:__ _v1/organizations/:id_
 * __Method:__ _GET_

 * __URL params:__ <br/>
    * required: <br/>
        `id=[uuid]`
    * optional: - <br/>
    
 * __Query params:__ <br/>
    * required: - <br/>
    * optional: - <br/>
        
 * __Success response:__
    * Code: 200 OK <br/>
    * Content: TODO - will be added
    ```
    ```

### Save new organization

Create a new organization.

 * __URI:__ _v1/organizations/_
 * __Method:__ _POST_

 * __URL params:__ <br/>
    * required: - <br/>
    * optional: - <br/>
    
 * __Query params:__ <br/>
    * required: - <br/>
    * optional: - <br/>
 
 * __Data params:__ <br/>
    * required: <br/>
        organizationDto=[OrganizationDto] TODO - will be added <br/>
         ``` 
         ```
    * optional: - <br/>

 * __Success response:__
    * Code: 200 OK <br/>
    * Content: TODO - will be added
    ```
    ```

### Update existing organization

Modify information associated with an existing organization.

 * __URI:__ _v1/organizations/:organizationId_
 * __Method:__ _PUT_

 * __URL params:__ <br/>
    * required: <br/>
        `organizationId=[uuid]` <br/>
    * optional: - <br/>
    
 * __Query params:__ <br/>
    * required: - <br/>
    * optional: - <br/>
 
 * __Data params:__ <br/>
    * required: <br/>
        organizationDto=[OrganizationDto] TODO - will be added <br/>
         ``` 
         ```
    * optional: - <br/>

 * __Success response:__
    * Code: 200 OK <br/>
    * Content: TODO - will be added
    ```
    ```

### Delete Organization

Remove existing organization.

 * __URI:__ _v1/organizations/:organizationId_
 * __Method:__ _DELETE_

 * __URL params:__ <br/>
    * required: <br/>
        `organizationId=[uuid]` <br/>
    * optional: - <br/>
    
 * __Query params:__ <br/>
    * required: - <br/>
    * optional: - <br/>

 * __Success response:__
    * Code: 204 NO CONTENT <br/>
