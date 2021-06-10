FROM adoptopenjdk/openjdk11:latest

WORKDIR /

COPY target/account-service-*.jar /app.jar
CMD ["java", "-jar", "app.jar", "application.yml"]