FROM eclipse-temurin:17-jdk-jammy
LABEL authors="SPIRIT"
WORKDIR /app
#copy the pom.xml to the app image
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
#Run this to install all the dependencies
RUN ./mvnw dependency:resolve
#Then copy the src folder
COPY src ./src
# Copy the JAR file into the container
COPY target/*.jar /app/app.jar

# Expose port 8080
EXPOSE 8080

# CMD ["./mvnw", "spring-boot:run"]
CMD ["java", "-jar", "app.jar"]