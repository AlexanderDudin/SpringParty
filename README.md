## About

This is an example project that illustrates creating a RESTful API in Spring Boot.

## Runnning this project

```
CREATE SCHEMA IF NOT EXISTS testdb

mvn spring-boot:run
```

## Get token

```
curl -X POST --user 'gpsolutions:secret' -d 'grant_type=password&username=peter@example.com&password=password' http://localhost:8000/gpsolutions/oauth/token
```

## Example commands

Getting all people from the API:
```
curl -i -H "Accept: application/json" -H "Content-Type: application/json" -H "Authorization: Bearer $TOKEN" -X GET http://localhost:8000/gpsolutions/people
```
## Swagger

rest methods described on swagger: http://localhost:8000/gpsolutions/swagger-ui.html