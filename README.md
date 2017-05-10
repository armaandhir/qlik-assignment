# qlik-assignment
A simple microservice exposing rest endpoints built with Java Spring boot framework and can be deployed in docker containers.

#### Technologies Used
* Java Spring boot framework
* Hibernate ORM and JPA
* MySQL
* Gradle
* Docker

#### Tools
* Spring Tool Suite (STS)
* Postman

## Building and Running the service

### Locally
Clone the repository and make sure you have java 8 and Gradle installed

Start your MySQL server locally and create a database called qlik-assignment.
Make sure the database uri is then updated in application.properties

To Build and run the system with one command, navigate to the directory and type
```
gradle bootRun
```

