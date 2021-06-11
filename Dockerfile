# Intermediate image
FROM maven:3.6.3-jdk-11-slim AS build
ARG REPOSITORY_URL
ARG SERVER_USERNAME
ARG SERVER_PASSWORD
# build jar
RUN mkdir -p /workspace
WORKDIR /workspace
COPY build-settings.xml /workspace
COPY pom.xml /workspace
COPY src /workspace/src
RUN mvn -B -f pom.xml clean package -DskipTests --settings build-settings.xml

# Final image
FROM openjdk:11-jdk-slim
COPY --from=build /workspace/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]