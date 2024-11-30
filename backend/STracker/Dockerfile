FROM gradle:8.9-jdk21-alpine AS build

WORKDIR /app

COPY ./src ./src
COPY ./build.gradle ./build.gradle
COPY ./settings.gradle ./settings.gradle

RUN gradle assemble

FROM openjdk:21-jdk-slim

WORKDIR /stracker

COPY --from=build /app/build/libs/ssbes-0.0.1-SNAPSHOT.jar ./application.jar

EXPOSE 8888

ENTRYPOINT ["java","-jar","application.jar"]