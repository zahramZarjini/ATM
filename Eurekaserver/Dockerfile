FROM openjdk:1.8-alpine

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} eurekaserver-service.jar

ENTRYPOINT ["java","-jar","/eurekaserver-service.jar"]