FROM openjdk:17-jdk-alpine

COPY target/api-equipos-futbol.jar api-equipos-futbol.jar

EXPOSE 8080

CMD ["java", "-jar", "api-equipos-futbol.jar"]