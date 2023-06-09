FROM docker.io/library/openjdk:17-alpine
COPY . .
RUN ./gradlew build -x test
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=prod", "/build/libs/meokjang-0.0.1-SNAPSHOT.jar"]

