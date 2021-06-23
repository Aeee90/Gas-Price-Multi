# Start with a base image containing Java runtime
FROM adoptopenjdk/openjdk11

# Add Author info
LABEL maintainer="spoi2308@gmail.com"

# Add a volume to /tmp
VOLUME /tmp

# Make port 8080 available to the world outside this container
EXPOSE 8080

# The application's jar file
ARG JAR_FILE=build/libs/api-1.0-SNAPSHOT.jar


# Add the application's jar to the container
ADD ${JAR_FILE} gas-price-api.jar

# Run the jar file
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/gas-price-api.jar"]
