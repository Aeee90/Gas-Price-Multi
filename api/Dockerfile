FROM adoptopenjdk/openjdk11 as builder
WORKDIR application
ARG JAR_FILE=build/libs/api-1.0-SNAPSHOT.jar
COPY ${JAR_FILE} gas-price-api.jar
RUN java -Djarmode=layertools -jar gas-price-api.jar extract

# Start with a base image containing Java runtime

FROM adoptopenjdk/openjdk11
LABEL maintainer="spoi2308@gmail.com"
VOLUME /tmp
EXPOSE 8080

WORKDIR application
COPY --from=builder application/dependencies ./
COPY --from=builder application/spring-boot-loader ./
COPY --from=builder application/snapshot-dependencies ./
COPY --from=builder application/application ./


# Run the jar file
ENTRYPOINT ["java","org.springframework.boot.loader.JarLauncher"]

