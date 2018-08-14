# AstroAssignment
This project uses Spring Boot 2 to build a web application for Schedule feature.

## How to build:
* Clone source code from Github.
* At source code folder, execute this command: `mvn clean package` (Note: this project uses Java 8).
* It will create 1 war file "SupportWheel-0.0.1-SNAPSHOT.war" in the folder "target".

## How to run the application:
* At the source code folder, execute this command: `java -jar ./target/SupportWheel-0.0.1-SNAPSHOT.war`.
* It will start 1 Tomcat server in your local host (port: 8080).
* If you want to check schedule API, use this url: http://localhost:8080/schedule. It'll return Json string.
* If you want to view the schedule in web page, use this url: http://localhost:8080/viewSchedule. 
