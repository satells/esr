# Start with maven:3.8.7-eclipse-temurin-19-alpine base image
FROM maven:3.8.7-eclipse-temurin-19-alpine

# Maintaner data 
LABEL maintainer="Mauro Sergio <msergiost@hotmail.com>"

# Set the working directory to /app
WORKDIR /app

# Copy the source code to the container
COPY . .

# Build the application with Maven
RUN mvn package

# Expose default Spring Boot port
EXPOSE 8080
# Run the jar file
CMD ["java", "-jar", "target/esr-0.0.1-SNAPSHOT", "--spring.profiles.active=prod"]

#End
