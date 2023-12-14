# SmartJob Demo

Welcome to SmartJob API, a powerful and user-friendly RESTful application designed to streamline user registration processes. 
Whether you're building a new application or enhancing an existing system, SmartJob API provides essential functionalities like email validation, password formatting, and more.

### Requirements

The requirements for SmartJob API are provided in the following file:

[Ejercicio JAVA](requirements/Ejercicio_JAVA-Especialista_Integracion.PDF)

### Tech Stack

- **Java:** 11 Programming language used for backend development.
- **Spring Boot:** 2.5.15 Framework for building Java-based enterprise applications.
- **Swagger:** 3.0.0 API documentation tool for creating interactive API documentation.
- **Gradle:** 8.5 Embedded gradle for building and project management tool.
- **Spring Data JPA:** Data access framework for Java applications.
- **H2 Database:** smartjob In-memory database for development and testing.
- **JSON Web Tokens (JWT):** 0.12.3 Token-based authentication for API security.

### Project Structure

The project structure outlines the organization of files and directories within the SmartJob API project. 
It provides a clear overview of how the codebase is structured and where to find essential components. 
Below is a representation of the project structure:

```
demo
│   README.md
│   ...
└───src
│   └───main
│       └───java
│           └───cl.smartjob.demo
│               │   DemoApplication.java
│               │
│               └───controller
│                   │   UserController.java
│                   │
│               └───dto
│                   │   UserDto.java
│                   │
│               └───entity
│                   │   User.java
│                   │   Phone.java
│                   │
│               └───repository
│                   │   UserRepository.java
│                   │
│               └───service
│                   │   UserService.java
│                   │   UserServiceImpl.java               
│       └───test
|           └───cl.smartjob.demo
|               DemoApplicationEndpointTests.java
|               ...
└───resources
    │   application.properties
    │   ...
```

src/main: Contains the main source code for the application.
* java: Java source files.
* resources: Configuration files and static resources. 

src/test: Houses test-related source code.
* java: Test classes.

README.md: Documentation for the project.

### How to Run

The repository provides an embedded Gradle for quick installation.

1. Run the build command to execute tests and generate an executable JAR:
```bash
./gradlew build
```
2. Once all tests have passed, you can run the application using the bootRun task:
```bash
./gradlew bootRun
```
3. Alternatively, in the repository, you can find a Dockerfile to run the application in a simple container. 
Build the Docker image using the following command:
```bash
docker build -t smartjob/demo .
```
4. Finally, run the Docker container:
```bash        
docker run -p 8080:8080 -d smartjob/demo
```

### API Documentation

#### Database Schema

[Schema Script](src/main/resources/schema.sql)

#### SmartJob API Diagrams

[API Sequence Diagram](docs/demo_sequence_diagram.pdf)

[API Class Diagram](docs/class_sequence_diagram.pdf)

### Testing

First Run the SmartJob API locally or access the deployed instance.

#### Swagger UI

Explore and interact with the API using the Swagger UI by navigating to [Swagger UI](http://localhost:8080/swagger-ui/index.html) 
(replace with your actual API endpoint). Review request payloads, test API operations, and more directly within the Swagger interface.

#### cURL Commands

Alternatively, you can use cURL commands to interact with the API. Execute the following cURL command to create a new user:

```bash
curl -X POST "http://localhost:8080/user" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"email\": \"juan@rodriguez.org\", \"name\": \"Juan Rodriguez\", \"password\": \"hunter2\", \"phones\": [ { \"citycode\": 1, \"countrycode\": 57, \"number\": 1234567 } ]}"
```

Ensure you're making a POST request to http://localhost:8080/user with a JSON structure like:

```json
{
  "email": "juan@rodriguez.org",
  "name": "Juan Rodriguez",
  "password": "hunter2",
  "phones": [
    {
      "citycode": "1",
      "countrycode": "57",
      "number": "1234567"
    }
  ]
}
```

#### H2 Database

To explore and interact with the H2 database

* Open the H2 console by navigating to http://localhost:8080/h2-console (replace with your actual API endpoint).
* In JDBC URL, use the parameter **jdbc:h2:mem:smartjob**, Username **sa**, and no password