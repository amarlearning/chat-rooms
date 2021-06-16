## Chat Rooms

![badge](https://action-badges.now.sh/amarlearning/chat-rooms)
[![Issues](https://camo.githubusercontent.com/926d8ca67df15de5bd1abac234c0603d94f66c00/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f636f6e747269627574696f6e732d77656c636f6d652d627269676874677265656e2e7376673f7374796c653d666c6174)](https://github.com/amarlearning/spring-boot-websocket/issues)
[![MIT LICENSE](https://img.shields.io/pypi/l/pyzipcode-cli.svg)](http://amarlearning.mit-license.org/)

> Real time public/private chat application using spring boot web-sockets

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
