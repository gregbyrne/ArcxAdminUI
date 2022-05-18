FROM openjdk:11-jre

EXPOSE 7100

COPY target/arcx-0.0.1-SNAPSHOT.jar arcx.jar

CMD ["java","-Dspring.profiles.active=dev", "-jar", "arcx.jar"]
