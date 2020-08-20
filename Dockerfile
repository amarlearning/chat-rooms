FROM openjdk:8

WORKDIR /java

ADD target/chat-rooms.jar chat-rooms.jar

EXPOSE 8080

ENTRYPOINT [ "java", "-jar", "chat-rooms.jar" ]