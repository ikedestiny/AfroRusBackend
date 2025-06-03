# ---- Build stage ----
FROM gradle:8.6-jdk17-jammy as builder
WORKDIR /workspace/app

# Copy and cache dependencies
COPY settings.gradle .
COPY build.gradle .
RUN gradle dependencies --no-daemon

# Copy source code and build application
COPY src ./src
RUN gradle bootJar --no-daemon -x test

# Extract Spring Boot layers
RUN mkdir -p build/extracted && \
    java -Djarmode=layertools -jar build/libs/*.jar extract --destination build/extracted

# ---- Runtime stage ----
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app

# Copy extracted layers
COPY --from=builder /workspace/app/build/extracted/dependencies/ ./
COPY --from=builder /workspace/app/build/extracted/spring-boot-loader/ ./
COPY --from=builder /workspace/app/build/extracted/snapshot-dependencies/ ./
COPY --from=builder /workspace/app/build/extracted/application/ ./

EXPOSE 8080
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]
