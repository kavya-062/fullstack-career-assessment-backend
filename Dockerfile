FROM eclipse-temurin:21-jdk

WORKDIR /app

ARG CACHEBUST=1
COPY . .

RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests

CMD ["java", "-jar", "target/careerbackend-0.0.1-SNAPSHOT.jar"]