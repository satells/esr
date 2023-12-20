FROM openjdk:17-alpine

WORKDIR /app

ARG JAR_FILE

COPY target/${JAR_FILE} /app/api.jar

EXPOSE 8080


CMD [ "java","-Dspring.profiles.active=prod", "-jar", "api.jar" ]