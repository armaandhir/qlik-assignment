# qlik-assignment
A simple microservice exposing rest endpoints built with Java Spring boot framework and can be deployed in docker containers.

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
The service has 4 endpoints.

Use POST\DELETE */qlik/api/message*
for insering or deleting a message

Use GET */qlik/api/message*
for displaying all messages

Use GET */qlik/api/message/{id}*
for displaying a specific message


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

## Scope of Improvements
* Currently there is no security. A good practise is to use OAuth2 for securing rest end points and integrate SSL. Spring framework allows to easily integrate security to a web service.
* getAllMessages() currently gets all the messages from the database which is not a good practise. Use of pagination or getting fixed amount of results is recommended
* Deleting a row from the database is contradictory. Enabling or disabling a row is preferred.
* More endpoints can be added for getting posted messages by a user, retrieve only recieved messages etc.




