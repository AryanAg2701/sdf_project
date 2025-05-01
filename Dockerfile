# ───► Stage 1: build with OpenJDK + Ant
FROM openjdk:11-jdk-slim AS builder

# install Ant
RUN apt-get update \
 && apt-get install -y --no-install-recommends ant \
 && rm -rf /var/lib/apt/lists/*

WORKDIR /app

# copy build scripts and sources
COPY build.xml run_build.py ./
COPY src ./src

# run Ant to compile & package jar
RUN ant clean jar

# ───► Stage 2: runtime with just the JAR
FROM openjdk:11-jre-slim

WORKDIR /app

# copy only the built jar
COPY --from=builder /app/dist/MyInfArith.jar ./MyInfArith.jar

# default entrypoint: you can override CMD to pass different args
ENTRYPOINT ["java", "-jar", "MyInfArith.jar"]
CMD []
