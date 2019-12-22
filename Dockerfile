FROM openjdk:11.0.4-jre-slim

RUN mkdir /app

WORKDIR /app

ADD ./api/target/api-RELEASE-0.8.jar /app

EXPOSE 8083

CMD ["java", "-jar", "api-RELEASE-0.8.jar"]
