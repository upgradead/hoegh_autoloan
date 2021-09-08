FROM openjdk:8-jre-alpine

EXPOSE 8080

COPY ./build/libs/autoloan-1.0-0.jar /usr/app/
WORKDIR /usr/app

ENTRYPOINT ["java", "-jar", "autoloan-1.0-0.jar.jar"]
