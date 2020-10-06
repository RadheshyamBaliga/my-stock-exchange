FROM openjdk:8-jdk-alpine
MAINTAINER radheshyambaliga@gmail.com
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} broker-service.jar
ENTRYPOINT [ "java", "-jar", "/broker-service.jar" ]