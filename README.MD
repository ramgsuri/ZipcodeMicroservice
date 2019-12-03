# Zip Code Fetcher

## General info
This project provides list of zip codes to the client on the basis of some filter :

## REST API using Spring Boot


## POST http://localhost:9090/census
## GET  http://localhost:9090/census/zipcodes/range?start=1&end=500
## GET  http://localhost:9090/census/zipcodes/medianage?start=1&end=232.4
## GET  http://localhost:9090/census/zipcodes/toppolulated?top=3
## GET  http://localhost:9090/census/zipcodes/genderdiff 


## Technologies
Project is created with:
* Java 8
* Junit
* Maven
* Lombok - 1.18.10 ( This is used to remove boilerplate code for getter and setters of POJO)
* Spring Boot - 2.1.5.RELEASE

## Project Setup
## Usage
To assemble the project run:
Once you run the command, this jar gets created by the name of ZipCodeFetcher-0.0.1-SNAPSHOT.jar
```
$ mvn clean install 
```

To run JUnit tests:
```
$ mvn test
```

## Steps Of Execution :

Run db script 
#Flyway or Liquibase can be used for database migration
# Run create database census in mysql 

# Run swagger by this url : Swagger is used for api doc
http://localhost:9090/swagger-ui.html#/

# Run the jar file 
java -jar ZipCodeFetcher-0.0.1-SNAPSHOT.jar


