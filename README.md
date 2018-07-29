# Furlenco Assignment

This project creates a basic backend application for storing student details. 

## Setup:

Installation:   mvnw clean install
Run application: mvnw spring-boot:run

## Technical Details:

The api is created using spring boot. The application is attempted to be extendable in terms of the features provided (including apis 
and filters). 

In memory H2 DB is used for storing the data. JDBC is used for communication to the same. H2 DB was chosen for this application since it will not
require additional setup and scripts and the initial db setup can be a part of the codebase and at the same time it provides decent interface
for testing the application.

All queries are configured in Queries.java for configurability.

## H2 DB Console:
Login url: http://localhost:8080/h2-console/login.do
Username: sa
Password: <blank>
jdbc url: jdbc:h2:mem:testdb

## Tests
Postman test cases included in src/test/resources

## API Details

GET /students
QueryParams:
  classes: comma seperated numbers - result contains students only from classes given here (default: all)
  active: true/false - result contains only students with given active criterion (default: all)
  admissionYearAfter: Number - result contains students with admission year after supplied year (default: all)
  pageNumber: Number - allows pagination of results, starting from page 0 (default: 0)
  pageSize: Number - allows pagination of results - every page contains a maximum of given number of records (default: 10)

Response: 200 on success


GET /students/{id}
Returns the student with the given id
Response:
  200 on success
  404 on invalid id

POST /students
Create a student
Response:
  202 on successful creation

PATCH: /students/{id}
Updates the student class. (Other udpates result in a validation error)
Response: 
  200 on successful update
  400 Bad request on wrong id

DELETE: /students/{id}
Makes the student inactive.
Response:
  200 on successful update
  400 on invalid id
