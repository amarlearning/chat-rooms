package me.amarpandey.controller;

import java.time.LocalDateTime;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import me.amarpandey.model.User;
import me.amarpandey.model.UserResponse;

@Controller
public class ChatController {

	@MessageMapping("/user")
	@SendTo("/topic/user")
	public User getUser(User user) {
		return new User(user.getName() + "  Joined!");
	}

	@MessageMapping("/message")
	@SendTo("/topic/message")
	public UserResponse getMessage(UserResponse userResponse) {
		return new UserResponse(userResponse.getName(), userResponse.getContent(), String.valueOf(LocalDateTime.now()));
	}

}
