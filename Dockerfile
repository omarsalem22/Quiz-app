# Use a multi-stage build for efficiency

# Stage 1: Build the application using Maven
FROM maven:3.9.5-eclipse-temurin-21 AS builder

# Set the working directory
WORKDIR /app

# Copy the pom.xml file and download dependencies (caching layer)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy the source code and build the application
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Create the runtime image
FROM eclipse-temurin:21-jre-alpine

# Set the working directory
WORKDIR /app

# Copy the built JAR from the builder stage
COPY --from=builder /app/target/Quiz-0.0.1-SNAPSHOT.jar app.jar

# Expose the port (default Spring Boot port is 8080)
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]