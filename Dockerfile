FROM openjdk:11-jdk-alpine
MAINTAINER experto.com
VOLUME /tmp
EXPOSE 8080
ADD build/libs/employeeapi-0.0.1-SNAPSHOT.jar employeeapi.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/springbootpostgresqldocker.jar"]