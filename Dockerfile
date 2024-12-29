FROM openjdk:23-jdk

WORKDIR /app

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "projectserviceapplication.jar"]
