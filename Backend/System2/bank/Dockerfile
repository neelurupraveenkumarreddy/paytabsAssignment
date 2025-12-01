# ------------ Build Stage ------------
FROM eclipse-temurin:21-jdk-jammy AS builder
WORKDIR /app

# Copy Maven files first for caching
COPY pom.xml .
COPY .mvn .mvn
COPY mvnw .
COPY mvnw.cmd .

# Fix permission issue
RUN chmod +x mvnw

# Download dependencies (cached layer)
RUN ./mvnw dependency:go-offline

# Copy source code and build the project
COPY src ./src
RUN ./mvnw -DskipTests package

# ------------ Runtime Stage ------------
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

# Copy JAR built from builder stage
COPY --from=builder /app/target/*.jar app.jar

# Expose application port
EXPOSE 8081

# Run app
ENTRYPOINT ["java", "-jar", "app.jar"]
