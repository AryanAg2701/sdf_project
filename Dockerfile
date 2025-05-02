FROM eclipse-temurin:11-jdk

COPY . /app
WORKDIR /app
RUN apt-get update && apt-get install -y ant && ant clean jar

CMD ["java", "-jar", "/app/dist/MyInfArith.jar"]
