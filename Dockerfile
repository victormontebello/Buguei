# ---- Build Stage (Ubuntu) ----
FROM ubuntu:22.04 AS builder

# Install JDK and Maven
RUN apt-get update && \
    apt-get install -y openjdk-17-jdk maven git && \
    apt-get clean

ENV JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
ENV PATH="$JAVA_HOME/bin:$PATH"

WORKDIR /app

COPY . .

# Build the Spring Boot JAR
RUN mvn clean package -DskipTests


# ---- Runtime Stage (Ubuntu) ----
FROM ubuntu:22.04

# Install JDK
RUN apt-get update && \
    apt-get install -y openjdk-17-jdk && \
    apt-get clean

ENV JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
ENV PATH="$JAVA_HOME/bin:$PATH"

WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
