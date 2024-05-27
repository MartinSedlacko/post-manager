# Post Manager

## Requirements
* Java 21
* Maven
* Docker

## Run application (dev)

### Start MySQL database
* Navigate to `docker/amcef-assignment-dev`
* `run docker-compose up`

### Start application
Either run application via your IDE or command line

Via command line:

* `mvn clean package`
* `java -jar target/*.jar --enable-preview`


## Run application in docker

* `mvn clean package`
* navigate to `docker/amcef-assignment-full`
* run `docker-compose up`
