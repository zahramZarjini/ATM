FROM openjdk:1.8-alpine

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} gateway-service.jar

ENTRYPOINT ["java","-jar","/gateway-service.jar"]