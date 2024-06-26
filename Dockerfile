FROM openjdk:17-jdk-alpine

COPY ./target/datajpa-0.0.1-SNAPSHOT.jar /app/datajpa-0.0.1-SNAPSHOT.jar

WORKDIR /app

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "datajpa-0.0.1-SNAPSHOT.jar"]

