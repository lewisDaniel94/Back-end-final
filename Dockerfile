FROM amazoncorretto:19
MAINTAINER lws
COPY target/mgb-0.0.1-SNAPSHOT.jar  lws-app.jar
ENTRYPOINT ["java","-jar","/lws-app.jar"]






