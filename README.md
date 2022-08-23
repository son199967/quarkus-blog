Run Flow


```shell script
mvn clean package
```
Build Image
```shell script
docker build -f src/main/docker/Dockerfile.jvm -t quarkus/quarkus-blog-jvm .
```
Run Docke rCompose
```shell script
docker-compose up 
```