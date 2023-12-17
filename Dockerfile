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


#Update APT repository & Install gnupg
RUN apt-get update && apt-get install -y gnupg

#Add an account for running MySQL
RUN groupadd -r mysql && useradd -r -g mysql mysql

#Add the MySQL APT repository & Install necessary programs
RUN apt-get update \
  && echo "deb http://repo.mysql.com/apt/ubuntu/ bionic mysql-5.7" > \
  /etc/apt/sources.list.d/mysql.list \
  && apt-key adv --keyserver pgp.mit.edu --recv-keys 5072E1F5 \
  && apt-get update \
  && apt-get install -y --no-install-recommends perl pwgen

#Install MySQL
RUN { \
  #Set MySQL root password for silent installation
  echo mysql-community-server mysql-community-server/root-pass password ''; \
  echo mysql-community-server mysql-community-server/re-root-poss password ''>
} | debconf-set-selections \
  && apt-get install -y mysql-server \
  && mkdir -p /var/lib/mysql /var/run/mysqld \
  && chown -R mysql:mysql /var/lib/mysql /var/run/mysqld \
  && chmod 777 /var/run/mysqld

#Solve the problem that ubuntu cannot log in from another container
RUN sed -i 's/bind-address/#bind-address/' /etc/mysql/mysql.conf.d/mysqld.cnf

#Mount Data Volume
VOLUME /var/lib/mysql

#Expose the default port
EXPOSE 3306

#Start MySQL
CMD ["mysqld","--user","mysql"]
CMD ["java", "-jar", "target/esr-0.0.1-SNAPSHOT", "--spring.profiles.active=prod"]


#End
