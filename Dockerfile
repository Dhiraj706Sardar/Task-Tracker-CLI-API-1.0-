FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copy only the built JAR
COPY target/TaskTrackerCLI-0.0.1-SNAPSHOT.jar app.jar

CMD ["java", "-jar", "app.jar"]
