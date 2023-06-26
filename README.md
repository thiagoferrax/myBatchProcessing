# myBatchProcessing

## About

myBatchProcessing is a batch processing application that reads payment data from a CSV file and populates a database with that data.

## Features

- Reads payment data from a CSV file
- Populates a database with the payment data

## Architecture overview

#### Project structure

```
├── docker-compose.yml
├── Dockerfile
├── HELP.md
├── LICENSE
├── mvnw
├── mvnw.cmd
├── payments.csv
├── pom.xml
├── README.md
└── src
    ├── main
    │   ├── java
    │   │   └── com
    │   │       └── thiagoferraz
    │   │           └── myBatchProcessing
    │   │               ├── configurations
    │   │               ├── controllers
    │   │               │   └── PaymentController.java
    │   │               ├── entities
    │   │               │   └── Payment.java
    │   │               ├── MyBatchProcessingApplication.java
    │   │               ├── repositories
    │   │               │   └── PaymentRepository.java
    │   │               └── utils
    │   │                   └── CSVGenerator.java
    │   └── resources
    │       └── application.properties
    └── test
        └── java
            └── com
                └── thiagoferraz
                    └── myBatchProcessing
```

#### Tech stack

* [Spring Batch](https://spring.io/projects/spring-batch) for processing large volumes of records, including logging/tracing, transaction management, job processing statistics, job restart, skip, and resource management.
* [Spring Boot](http://spring.io/projects/spring-boot) for creating the RESTful Web Services
* [MockMVC](https://spring.io/guides/gs/testing-web/) for testing the Web Layer
* [Mockito](https://site.mockito.org/) for testing the Services Layer
* [MySQL](https://www.mysql.com/) as database
* [Maven](https://maven.apache.org/) for managing the project's build
* [Docker](https://www.docker.com/) for building and managing the application distribution using containers

## Install

#### Download the repository

```sh
$ git clone https://github.com/thiagoferrax/myBatchProcessing.git
```

#### With docker and docker-compose installed

```sh
$ cd myBatchProcessing && docker-compose up
```
## Swagger UI Documentation

To access the Swagger UI documentation for this project, please follow the steps below:

1. Make sure that the project is running locally on your machine.
2. Open your web browser and navigate to [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html).
3. This interactive interface provides a comprehensive overview of the API endpoints, request/response schemas, and allows you to interact with the API directly.
4. It's a helpful tool for testing and exploring the functionality of the project.

## License

MIT © [thiagoferrax](https://github.com/thiagoferrax).
