# Byggesteg (Multi-stage build)
FROM maven:3.9.6-eclipse-temurin-21 AS builder
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Runtime steg
FROM openjdk:21-jdk
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar

# Sett oppstartskommandoen
ENTRYPOINT ["java", "-jar", "/app/app.jar"]


#docker build -t backend-question-service .

