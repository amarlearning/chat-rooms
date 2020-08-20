FROM maven:3-openjdk-8

WORKDIR /chat-rooms

COPY . ./

RUN mvn package

EXPOSE 8080

ENTRYPOINT [ "java", "-jar", "target/chat-rooms.jar" ]