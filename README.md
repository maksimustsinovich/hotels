# Hotels Management System

RESTful API application for hotel management with search, filtering, and statistical capabilities.

## Description

The system provides functionality for:
- Creating and managing hotel information
- Adding amenities to hotels
- Searching hotels by various criteria (name, brand, city, country, amenities)
- Retrieving list of all hotels
- Getting detailed information about specific hotels
- Generating histograms by various parameters (brand, city, country, amenities)

## Technologies Used

### Backend
- **Java** - primary programming language
- **Spring Boot** - web application framework
- **Spring Data JPA** - database access
- **Spring Web** - REST API creation
- **Hibernate** - ORM framework
- **MapStruct** - code generation for object mapping
- **Lombok** - boilerplate code reduction
- **SpringDoc OpenAPI** - API documentation (Swagger UI)

### Database
- **H2 Database** - in-memory database for development
- **Liquibase** - database migration management

### Testing
- **JUnit 5** - unit testing framework
- **Spring Boot Test** - integration testing

### Build Tools
- **Maven** - project build system

## Main Endpoints

- `POST /property-view/hotels` - create hotel
- `POST /property-view/hotels/{id}/amenities` - add amenities to hotel
- `GET /property-view/hotels` - get all hotels
- `GET /property-view/hotels/{id}` - get specific hotel details
- `GET /property-view/search` - search hotels by parameters
- `GET /property-view/histogram/{param}` - get histogram by parameter

## Running the Application

```bash
mvn spring-boot:run
```