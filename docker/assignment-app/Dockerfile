FROM eclipse-temurin:21-jdk

# Add the application's jar to the container
COPY ./app.jar app.jar

# Expose the port the app runs on
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java", "--enable-preview", "-jar", "/app.jar"]
