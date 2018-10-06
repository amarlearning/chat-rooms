package me.amarpandey.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import me.amarpandey.model.User;
import me.amarpandey.model.UserResponse;

@Controller
public class ChatController {

	@MessageMapping("/user")
	@SendTo("/topic/user")
	public UserResponse getUser(User user) {
		return new UserResponse("Hi : " + user.getName());
	}

}
