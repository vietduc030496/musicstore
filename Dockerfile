FROM maven:3.8.3-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests


FROM --platform=amd64 openjdk:17.0.2-oraclelinux8
WORKDIR /app
COPY --from=build /app/target/musicstore-0.0.1-SNAPSHOT.jar  app.jar
EXPOSE 8090
CMD ["java", "-jar", "app.jar"]