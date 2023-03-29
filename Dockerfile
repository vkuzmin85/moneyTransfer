FROM openjdk:17-jdk-alpine

ADD build/libs/moneyTransfer-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 5500

ENTRYPOINT ["java","-jar","app.jar"]
