FROM  eclipse-temurin:17-jdk-jammy AS build-image
WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./

RUN ./mvnw dependency:go-offline

COPY ./src/main/ ./src/main/

RUN ./mvnw clean package

FROM eclipse-temurin:17-jre-jammy AS deploy-image

COPY  --from=build-image /app/target/*.jar /app/app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]