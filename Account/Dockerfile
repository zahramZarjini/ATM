FROM openjdk:1.8-alpine

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} account-service.jar

ENTRYPOINT ["java","-jar","/account-service.jar"]