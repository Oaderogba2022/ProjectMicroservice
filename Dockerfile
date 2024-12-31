FROM openjdk:23-jdk-slim AS build

WORKDIR /app

COPY mvnw /app/
COPY .mvn /app/.mvn
COPY src /app/src
COPY pom.xml /app/

RUN chmod +x mvnw
RUN ./mvnw clean install -DskipTests

FROM openjdk:23-jdk-slim

WORKDIR /app

COPY --from=build /app/target/ProjectServiceApplication-0.0.1-SNAPSHOT.jar projectserviceapplication.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "projectserviceapplication.jar"]
