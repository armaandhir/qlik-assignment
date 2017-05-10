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
Make sure the database uri is then updated in application.properties.
No need to create tables as the 

To Build and run the system with one command, navigate to the directory and type
```
gradle bootRun
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



