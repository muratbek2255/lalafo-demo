FROM eclipse-temurin:25-jdk

WORKDIR /app

COPY muratkanov-muratbek-lalafo-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 9090

ENTRYPOINT ["java", "-jar", "app.jar"]