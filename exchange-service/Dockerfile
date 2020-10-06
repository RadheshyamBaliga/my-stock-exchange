FROM openjdk:8-jdk-alpine
MAINTAINER radheshyambaliga@gmail.com
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} exchange-service.jar
ENTRYPOINT [ "java", "-jar", "/exchange-service.jar" ]