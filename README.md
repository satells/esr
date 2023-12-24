# esr

docker build -t esr:v1 .

docker run --rm -d -p 8080:8080 -e DB_HOST=mysql --network esr-network esr:v1

docker image prune -a

docker network prune


docker network create --driver bridge esr-network

docker volume ls
docker volume rm <nome>
docker volume prune 

docker container rm esr --force --volumes

docker container run -d -p 3306:3306 -e MYSQL_ALLOW_EMPTY_PASSWORD=yes --network esr-network --name mysql mysql:8.0.35
docker run --rm -d -p 8080:8080 -e DB_HOST=mysql --network esr-network esr:v1

mvnw -DskipTests -Pdocker package
mvnw -DskipTests package
