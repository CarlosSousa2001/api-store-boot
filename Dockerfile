FROM amazoncorretto:17-alpine-jdk

COPY ./target/datajpa-0.0.1-SNAPSHOT.jar /app/datajpa-0.0.1-SNAPSHOT.jar

WORKDIR /app

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "datajpa-0.0.1-SNAPSHOT.jar"]

