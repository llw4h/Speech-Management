FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim
COPY --from=build /target/SpeechManagement-0.0.1-SNAPSHOT.jar SpeechManagement.jar
EXPOSE 8090
ENTRYPOINT ["java", "-jar", "SpeechManagement.jar"]

