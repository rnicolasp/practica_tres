FROM maven:3.9.6-eclipse-temurin-17-alpine AS base
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=base /app/target/practicatres-0.0.1-SNAPSHOT.jar gatito-app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "gatito-app.jar"]