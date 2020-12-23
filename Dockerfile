FROM maven:3.5.2-jdk-8-alpine AS MAVEN_BUILD
MAINTAINER JMP EIRL - Jesus Matos Portocarrero

COPY pom.xml /build/
COPY src /build/src/
WORKDIR /build/
RUN mvn package
FROM openjdk:8-jre-alpine
WORKDIR /app
COPY --from=MAVEN_BUILD /build/target/cliente-intercorp-microservicio-1.0.0.jar /app/
ENTRYPOINT ["java", "-jar", "cliente-intercorp-microservicio-1.0.0.jar"]