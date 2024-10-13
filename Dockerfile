# Prepare Maven environment
FROM maven:3.9.0-eclipse-temurin-17 AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the pom.xml and source code into the container
COPY pom.xml ./
COPY src ./src

# Package the application
RUN mvn clean package

# Stage 2: Run
FROM openjdk:17-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/target/online-bookshop.jar /app/app.jar

# Expose the port your app will run on
EXPOSE 8000

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]