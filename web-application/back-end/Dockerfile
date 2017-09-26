# Dockerfile
FROM openjdk:8u131-jre-alpine

RUN addgroup -S java0 && adduser -S -G java0 java0

RUN mkdir /data && chown -R java0:java0 /data

ADD /target/balance-service-1.0.0.jar /data/balance.jar

EXPOSE 8080

USER java0

ENTRYPOINT [ "sh", "-c", "java -jar /data/balance.jar server" ]