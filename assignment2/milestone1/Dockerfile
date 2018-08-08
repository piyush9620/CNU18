FROM gradle:jdk10 as builder

COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build

FROM openjdk:10-jre-slim
COPY --from=builder /home/gradle/src/build/libs/harya-easyimage-0.1.0.jar /app/
WORKDIR /app
CMD java -jar harya-easyimage-0.1.0.jar