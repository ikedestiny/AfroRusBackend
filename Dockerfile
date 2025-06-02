# Build stage
FROM gradle:8.6-jdk17-jammy as builder
WORKDIR /workspace/app

# Cache gradle dependencies
COPY build.gradle settings.gradle ./
RUN gradle dependencies --no-daemon

# Build application
COPY src src
RUN gradle bootJar --no-daemon -x test  # Changed from 'build' to 'bootJar'

# Extract layers
RUN mkdir -p build/extracted && \
    java -Djarmode=layertools -jar build/libs/*.jar extract --destination build/extracted

# Runtime stage
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app

# Copy extracted layers
COPY --from=builder /workspace/app/build/extracted/dependencies/ ./
COPY --from=builder /workspace/app/build/extracted/spring-boot-loader/ ./
COPY --from=builder /workspace/app/build/extracted/snapshot-dependencies/ ./
COPY --from=builder /workspace/app/build/extracted/application/ ./

ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]