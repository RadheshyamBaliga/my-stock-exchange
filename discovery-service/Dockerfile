FROM openjdk:8-jdk-alpine
MAINTAINER radheshyambaliga@gmail.com
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} discovery-service.jar
EXPOSE 8671
ENTRYPOINT [ "java", "-jar", "/discovery-service.jar" ]