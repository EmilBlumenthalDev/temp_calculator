FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app

COPY pom.xml ./
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre
WORKDIR /app

RUN apt-get update && apt-get install -y \
    libx11-6 libxext6 libxrender1 libxtst6 libxi6 libxrandr2 libxcursor1 libxinerama1 \
    libgtk-3-0 libgl1 libglx0 libegl1 libgles2 \
    mesa-utils libglu1-mesa libgl1-mesa-dri \
    libfreetype6 libfontconfig1 \
    wget unzip ca-certificates \
    && rm -rf /var/lib/apt/lists/*

RUN mkdir -p /javafx-sdk && \
    wget -O javafx.zip \
    https://download2.gluonhq.com/openjfx/21.0.2/openjfx-21.0.2_linux-aarch64_bin-sdk.zip && \
    unzip javafx.zip -d /javafx-sdk && \
    mv /javafx-sdk/javafx-sdk-21.0.2/lib /javafx-sdk/lib && \
    rm -rf /javafx-sdk/javafx-sdk-21.0.2 javafx.zip

ENV DISPLAY=host.docker.internal:0.0
ENV LIBGL_ALWAYS_INDIRECT=1
ENV PRISM_VERBOSE=true
ENV PRISM_FORCEGL=true

COPY --from=build /app/target/temp_calculator-1.0-SNAPSHOT.jar app.jar

CMD ["java", \
     "--module-path", "/javafx-sdk/lib", \
     "--add-modules", "javafx.controls,javafx.fxml", \
     "-Dprism.order=sw", \
     "-Dprism.verbose=true", \
     "-jar", "app.jar"]