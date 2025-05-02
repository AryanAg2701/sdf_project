FROM openjdk:11-jdk-slim AS builder

RUN apt-get update \
 && apt-get install -y --no-install-recommends ant \
 && rm -rf /var/lib/apt/lists/*

WORKDIR /app

COPY build.xml run_build.py ./

RUN ant clean jar

FROM openjdk:11-jre-slim

WORKDIR /app

COPY --from=builder /app/dist/MyInfArith.jar ./MyInfArith.jar

ENTRYPOINT ["java", "-jar", "MyInfArith.jar"]
CMD []
