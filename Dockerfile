#
# Build stage
#
FROM eclipse-temurin:17-jdk-jammy AS build
ENV HOME=/usr/app
RUN mkdir -p $HOME
WORKDIR $HOME


ADD . $HOME
RUN --mount=type=cache,target=/root/.m2 ./mvnw -DskipTests -f $HOME/pom.xml clean package

#
# Package stage
#
FROM openjdk:17-alpine 
ARG JAR_FILE=/usr/app/target/*.jar

RUN apk add --no-cache bash && \
    apk add --no-cache htop 



COPY wait-for-it.sh /wait-for-it.sh
ARG CACHEBUST=0
RUN chmod +x /wait-for-it.sh

COPY --from=build $JAR_FILE /app/api.jar
EXPOSE 8080
CMD [ "java", "-XX:MaxRAM=72m","-server", "-Xss512k", "-XX:+UseSerialGC", "-XX:+UnlockExperimentalVMOptions","-Dspring.profiles.active=prod", "-jar", "/app/api.jar"]
