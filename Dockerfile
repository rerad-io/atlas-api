FROM maven:3.8.5-openjdk-17 AS build

COPY pom.xml /build/pom.xml
COPY src /build/src
WORKDIR /build

RUN mvn clean package -DskipTests

FROM ubuntu:23.04

WORKDIR /app

RUN apt-get update && \
    DEBIAN_FRONTEND=noninteractive \
    apt-get -y install openjdk-17-jre && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

COPY --from=build /build/target/MedAtlas-0.0.1-SNAPSHOT.jar /app/MedAtlas-0.0.1-SNAPSHOT.jar
RUN groupadd --gid 10001 javauser && useradd --uid 10001 --gid 10001 javauser
RUN chown -R javauser:javauser /app
USER javauser
EXPOSE 8080

CMD "java" "-jar" "MedAtlas-0.0.1-SNAPSHOT.jar"

# here example command to launch 
# docker build -t atlas-api:local .
# docker run -p 8080:8080 atlas-api:local