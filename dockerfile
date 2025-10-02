FROM maven:latest AS build
WORKDIR /app

COPY pom.xml ./
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn clean package -DskipTests

FROM maven:latest
WORKDIR /app

COPY --from=build /app/target/temp_calculator-1.0-SNAPSHOT.jar app.jar

CMD ["java", "-jar", "app.jar"]
