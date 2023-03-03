FROM openjdk:17-jdk-alpine

EXPOSE 5500

COPY build/libs/moneyTransfer-0.0.1-SNAPSHOT.jar app.jar

CMD ["java","-jar","money_transfer.jar"]