# qlik-assignment
A simple microservice exposing rest endpoints built with Java Spring boot framework and can be deployed in docker containers.The simple UI to test this service can be found here: https://github.com/armaandhir/qlik-assignment-ui

## Building, Deployment and Accessing

### Locally
Clone the repository and make sure you have java 8 and Gradle installed

Start your MySQL server locally and create a database called qlik-assignment.
Make sure the database uri is then updated in application.properties.
No need to create tables as the JPA and hibernate will take care of it.

To Build and run the system with one command, navigate to the directory and type
```
gradle bootRun
```
The service can be then tested using POSTMAN.
Since service will be running locally, the url will be
```
http://localhost:8080/{rest endpoints}
```
To just build the system, type
```
gradle build
```

### Docker containers
Clone the repository and make sure you have java 8, Gradle and docker installed.
To run as docker containers, we would create a mysql container to connect to the app. The mysql image used is downloaed from docker hub.
#### Linux (Ubuntu)
First build the image of the application. The aoolication containes the docker file so the image can be created using a single command
```
gradle build buildDocker
```
Create both containers and run.
Create the mysql container and initialize the schema.
```
docker run -d -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=qlik-assignment -p 3306:3306 --name mysql_docker mysql:latest
```

Create the qlik-assigment container and dont forget to link with the mysql container
```
docker run -P -d --name qlik-assignment -p 80:8080 --link mysql_docker:localhost armaandhir/qlik-assignment:latest
```
And that's it. Nice and clean!
The service can be then tested using POSTMAN.
Since service will be running locally, the url will be
```
http://localhost:80/{rest endpoints}
```

## REST API
The service has 4 endpoints which produce JSON responses.

**POST** */qlik/api/message*
* Consumes content-type: application/x-www-form-urlencoded
* requires values of 2 keys: author, text. Author length has to be between 3-16 characters. Text can be atmost 250 characters long
* returns the posted message or throws exception

**GET** */qlik/api/message*
* Consumes content-type: application/json
* returns the list of all messages

**POST** */qlik/api/message/{id}*
* Consumes content-type: application/json
* requires the id of the specif message
* returns the specific message and determines if it is a palindrome or http staus NOT_FOUND if doesn't exist

**DELETE** */qlik/api/message/{id}*
* Consumes content-type: application/json
* requires the id of the message to be deleted
* returns nothing on success else throws exception


## Implementation and Architecture

#### Technologies Used
* *Java Spring boot framework(Spring4):* For creating rest controllers and mapping 
* *Hibernate ORM and JPA:* To connect with database, automated generation of tables in database
* *MySQL:* Stores all the information
* *Gradle:* Build automation. Also automates tests and ceation of docker containers
* *Docker:* Run and deploy the service in containers

#### Tools
* Spring Tool Suite (STS)
* Postman

#### Implementation Architecture
The restful web service is implemented using spring boot framework(Spring4) which allows the use of annotations to configure and map the end points. 
The service application has 4 layers:
* *Modal:* It containes the main Message class which gets persisted. It is defined using JPA and Hibernate configurations allow creating tables easlity into the databases. They expose all necessary setters/getters and business logic.
* *Service Layer:* Contains all service layer Interfaces/methods which coonect with Repository layer. 
* *Repository or database Layer:* The layer which connects to the database via Hibernate
* *Controller:* The main class that defines all rest endpoints and calls the neccessy service layer methods. The endpoints defined here are exposed which provide the interaction with the application service.
The UI built in any language can use the endpoints to use the service.

## Scope of Improvements
* Currently there is no security. A good practise is to use OAuth2 for securing rest end points and integrate SSL. Spring framework allows to easily integrate security to a web service.
* getAllMessages() currently gets all the messages from the database which is not a good practise. Use of pagination or getting fixed amount of results is recommended
* Deleting a row from the database is contradictory. Enabling or disabling a row is preferred.
* More endpoints can be added for getting posted messages by a user, retrieve only recieved messages etc.
* Unit testing needs to be added to test the business rules. JUnit wprds very well with spring framework.




