# Etapa de construcción (Build)
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Etapa de ejecución (Run)
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/rama-0.0.1-SNAPSHOT.jar app.jar
RUN ./mvnw clean package -Pproduction
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
