FROM openjdk:11

COPY target/money-transfer-service-0.0.1-SNAPSHOT.jar transfer_service.jar

ADD src/main/resources/application.properties src/main/resources/application.properties

EXPOSE 8080

ENTRYPOINT ["java","-jar","/transfer_service.jar"]