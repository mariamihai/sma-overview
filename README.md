[![Build Status](https://travis-ci.org/mariamihai/sma-licensing-service.svg?branch=master)](https://travis-ci.org/mariamihai/sma-licensing-service)

# Spring Microservices in Action - Licensing Service
The project represents my implementation based on the "Spring Microservices in Action" book.

## API Version
Currently the application is at _v1_.

## Run
Run with encryption key or from the docker-compose file (currently in the Configuration Service).
```
mvn spring-boot:run -Dspring-boot.run.arguments=--encrypt.key=MySuperExtremelySecretKey
```
### Running locally
Profile active = local


### API calls
#### Obtain license information
Calling the service to obtain license information based on an organization id and license id.

 * __URI:__ _v1/organizations/:organizationId/licenses/:id_

 * __Method:__ _GET_

 * __URL params:__ <br/>
    * required: <br/>
        `organizationId=[uuid]` <br/>
        `id=[uuid]`
    * optional: -
    
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

## Docker Image
Project Docker Image [here](https://hub.docker.com/repository/docker/mariamihai/sma-licensing-service).