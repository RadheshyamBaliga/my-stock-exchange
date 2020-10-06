FROM openjdk:8-jdk-alpine
MAINTAINER radheshyambaliga@gmail.com
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} api-gateway-service.jar
EXPOSE 8050
ENTRYPOINT [ "java", "-jar", "/api-gateway-service.jar" ]