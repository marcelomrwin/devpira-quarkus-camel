### MYSQL
docker run --name mysql -v /home/marcelosales/development/camel-quarkus/mysqldir:/docker-entrypoint-initdb.d -p 3306:3306 -e MYSQL_ROOT_PASSWORD=r3dh4t1! -d --rm mysql:latest

mysql -u root -P 3306 --protocol=tcp -p

### Produtos
mvn clean quarkus:dev

docker build -f src/main/docker/Dockerfile.jvm -t quarkus/produtos-ws .
docker run -it --rm --name=produtos-ws -p 8090:8090 quarkus/produtos-ws

http://localhost:8090/produtos?wsdl

### Novos pedidos
mvn clean quarkus:dev

mvn clean package -Pnative -DskipTests
docker build -f src/main/docker/Dockerfile.native -t quarkus/pedidos .
docker run -it --rm --name=pedidos -p 8085:8085 quarkus/pedidos
http://localhost:8085/api

### Rota Camel

mvn clean quarkus:dev

./mvnw package -Pnative -Dquarkus.native.container-build=true -DskipTests
docker build -f src/main/docker/Dockerfile.native -t quarkus/camel-project .

docker run -it --rm -p 8080:8080 --name=camel-quarkus --link mysql:mysql --link produtos-ws:produtos-ws --link pedidos:pedidos -e CLIENTES_DB_HOST=mysql -e IN_FOLDER=/tmp -e OUT_FOLDER=/tmp/out -e PRODUTOS_HOST=produtos-ws -e PEDIDOS_URL=pedidos --memory="64m" --memory-reservation="64m" quarkus/camel-project

http://localhost:8080/api


docker cp pedido.xml camel-quarkus:/work/
docker cp pedido2.xml camel-quarkus:/work/
docker cp pedido3.xml camel-quarkus:/work/

docker exec -it camel-quarkus /bin/bash
cp /work/pedido.xml /tmp