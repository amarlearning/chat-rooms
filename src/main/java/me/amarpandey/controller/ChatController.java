package me.amarpandey.controller;

import static java.lang.String.valueOf;
import static java.time.LocalDateTime.now;
import static me.amarpandey.model.UserResponse.MessageType.CHAT;
import static me.amarpandey.model.UserResponse.MessageType.JOIN;
import static me.amarpandey.utils.Constants.NEW_USER_JOINED;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

import me.amarpandey.model.UserResponse;

@Controller
public class ChatController {

	@MessageMapping("/connect")
	@SendTo("/topic/connect")
	public UserResponse connect(@RequestParam String username, SimpMessageHeaderAccessor headerAccessor) {
		headerAccessor.getSessionAttributes().put("username", username);
		UserResponse userResponse = new UserResponse(username, NEW_USER_JOINED, JOIN);
		return userResponse;
	}

	@MessageMapping("/message")
	@SendTo("/topic/message")
	public UserResponse getMessage(@Payload UserResponse userResponse) {

		userResponse.setType(CHAT);
		userResponse.setTime(valueOf(now()));

		return userResponse;
	}

}
