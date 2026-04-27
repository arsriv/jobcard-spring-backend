# Build stage
FROM maven:3.9.6-eclipse-temurin-17 AS builder
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Run stage
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar

# Create an empty .env file that can be populated at runtime
# Pass environment variables via: docker run -e JWT_SECRET=value -e MONGO_URI=value ...
ENV SPRING_PROFILES_ACTIVE=prod

EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
