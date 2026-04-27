# Use Maven + Java 17 image
FROM maven:3.9.6-eclipse-temurin-17

# Set working directory
WORKDIR /app

# Copy project files
COPY . .

# Build the project
RUN mvn clean package -DskipTests

# Expose port (Render uses PORT env)
EXPOSE 8080

# Run the jar
CMD ["sh", "-c", "java -jar target/*.jar --server.port=$PORT"]