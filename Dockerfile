FROM openjdk:11
COPY target/vacation-service-0.0.1-SNAPSHOT.jar vacation-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/vacation-service-0.0.1-SNAPSHOT.jar"]