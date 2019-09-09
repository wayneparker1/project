FROM openjdk:8-jre

COPY ./target/eCommerce-0.0.1-SNAPSHOT.jar /app.jar

ENTRYPOINT ["java", "-jar", "/app.jar", "--application.properties.db", "contactsdb", "--application.properties.name", "esther", "--application.properties.password", "mypassword123"]

ENV NAME=esther DB=contactsdb PASSWORD=mypassword123