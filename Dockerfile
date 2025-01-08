FROM openjdk:23-jdk-slim

WORKDIR /app

COPY target/ProjectServiceApplication-0.0.1-SNAPSHOT.jar /app

EXPOSE 8081

CMD ["java", "-jar", "ProjectServiceApplication-0.0.1-SNAPSHOT.jar", "--spring.profiles.active=docker"]
