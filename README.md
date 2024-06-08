## Chat Rooms

[![chat-rooms](https://github.com/amarlearning/chat-rooms/actions/workflows/maven.yml/badge.svg)](https://github.com/amarlearning/chat-rooms/actions/workflows/maven.yml)
[![MIT LICENSE](https://img.shields.io/pypi/l/pyzipcode-cli.svg)](http://amarlearning.mit-license.org/)

Real-time public/private chat application using Spring boot web-sockets

## Demo

[![Spring Boot Websocket](https://www.amarpandey.me/projects/images/spring-boot-websocket.png)](https://spring-ws-app.herokuapp.com/)

## Setup

**1. Clone the application**

```bash
git clone https://github.com/amarlearning/chat-rooms.git
```

**2. Build and run the app using maven**

```bash
cd chat-rooms
mvn package
java -jar target/*.war
```

Alternatively, you can run the app directly using Docker image -

```bash

docker pull amarpandey/chat-rooms

docker run -p 8080:8080 --name=chat-rooms-app chat-rooms
```

## Issues

You can report the bugs at the [issue tracker](https://github.com/amarlearning/chat-rooms/issues)

**OR**

You can [tweet me](https://twitter.com/iamarpandey) if you can't get it to work. In fact, you should tweet me anyway.

---

## License

Built with ♥ by Amar Prakash Pandey([@amarlearning](http://github.com/amarlearning)) under [MIT License](http://amarlearning.mit-license.org/)

You can find a copy of the License at http://amarlearning.mit-license.org/
