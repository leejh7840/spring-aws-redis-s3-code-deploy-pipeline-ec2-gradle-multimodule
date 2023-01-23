FROM openjdk:17-oracle
VOLUME /main-app
ADD redis-multimodule-api-web/build/libs/redis-multimodule-api-web-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
