[![Build Status](https://travis-ci.org/mariamihai/sma-licensing-service.svg?branch=master)](https://travis-ci.org/mariamihai/sma-licensing-service)
[![Docker](https://img.shields.io/docker/v/mariamihai/sma-licensing-service?sort=date)](https://hub.docker.com/r/mariamihai/sma-licensing-service)

# Spring Microservices in Action - Licensing Service
Spring Boot Microservice project.

  - [Description](#description)
  - [API Version](#api-version)
  - [Docker images](#docker-images)
  - [Implementation details](#implementation-details)
    - [Properties](#properties)
    - [Profiles](#profiles)
  - [API calls](#api-calls)
      - [Obtain all licenses](#obtain-all-licenses)
      - [Obtain license information](#obtain-license-information)
      - [Save new license](#save-new-license)
      - [Get licenses when fallback is used](#get-licenses-when-fallback-is-used)

## Description
The project represents my implementation based on the "Spring Microservices in Action" book.

The main service of the project. Contains licensing information that can be used in correlation with the [Organization Service](https://github.com/mariamihai/sma-organization-service) and the [New Organization Service](https://github.com/mariamihai/sma-organization-new-service).

An overview of all the projects involved can be found [here](https://github.com/mariamihai/sma-overview).

## API Version
_V1_ is the current implementation. No changes to the project are expected to be made in the future that will affect the existing endpoints.

## Docker images
Automatic building was implemented for the microservices associated with this project.
For simplicity, I am using the build numbers provided by Travis CI as the version number for each different image constructed.

## Implementation details
### Properties
- the name of the application, used by the other services 
```
spring.application.name=licensing-service
```
- application server port
```
server.port=8080
```
- adding the secret String for encryption as an environment variable
```
encrypt.key=MySuperExtremelySecretKey
```

### Profiles
Profiles active: `local`.

### API calls
#### Obtain all licenses
Calling the service to obtain all licenses based on the organization id.

Pagination currently is not implemented.

 * __URI:__ _v1/organizations/:organizationId/licenses/_
 * __Method:__ _GET_

 * __URL params:__ <br/>
    * required: <br/>
        `organizationId=[uuid]` <br/>
    * optional: - <br/>
    
 * __Query params:__ <br/>
    * required: - <br/>
    * optional: - <br/>
        
 * __Success response:__
    * Code: 200 <br/>
    * Content: TODO - will be added
    ```
    ```

#### Obtain license information
Calling the service to obtain license information based on an organization id and license id.

 * __URI:__ _v1/organizations/:organizationId/licenses/:id/?clientType=:clientType_
 * __Method:__ _GET_

 * __URL params:__ <br/>
    * required: <br/>
        `organizationId=[uuid]` <br/>
        `id=[uuid]`
    * optional: - <br/>
    
 * __Query params:__ <br/>
    * required: - <br/>
    * optional: <br/>
        `clientType=[uuid]` <br/>
        Possible values: `discovery` (default), `ribbon`, `feign`.
    
 * __Success response:__
    * Code: 200 <br/>
    * Content: 
    ```
    {
        "id": "fbf2c99f-71c6-4bbd-bbda-fb0873733fcf",
        "productName": "License Name",
        "type": "License type",
        "organizationId": "9157b03b-1a6c-43a8-a31b-d39eebf9654b"
    }
    ```
 * __Error Response:__ -
    * __Code:__ 400 BAD REQUEST <br/>
    * __Content:__ 
    ``` 
    {
        "timestamp": "2020-08-10T16:12:38.660+00:00",
        "status": 400,
        "error": "Bad Request",
        "message": "",
        "path": "/v1/organizations/9157b03b-1a6c-43a8-a31b-d39eebf9654b/licenses/aaaa"
    }
    ```

#### Save new license
Creating a new license. Current, the endpoint is not implemented.

#### Get licenses when fallback is used
If, for example, the database is slower than usual then a fallback method is called to obtain a "default" license object. The method that creates the fallback object is `LicenseServiceImpl.buildFallbackLicenseList()`. The lincense obtained will have the next characteristics:
    ```
    {
        "id": "00000000-0000-0000-0000-000000000000",
        "productName": "Sorry, no licensing information currently available",
        "type": null,
        "organizationId": "provided-organization-id"
    }
    ```
This is implemented when obtaining all licenses based on an organization id. The result will still be an list, as expected.
