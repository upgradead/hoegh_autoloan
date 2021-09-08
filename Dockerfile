FROM openjdk:8-jre-alpine

EXPOSE 8080

COPY ./build/libs/autoloan-1.0-0.jar /usr/bin/docker
WORKDIR /usr/bin/docker

ENTRYPOINT ["java", "-jar", "autoloan-1.0-0.jar.jar"]
