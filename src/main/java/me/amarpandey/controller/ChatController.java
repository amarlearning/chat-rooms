package me.amarpandey.controller;

import static java.lang.String.valueOf;
import static java.time.LocalDateTime.now;
import static me.amarpandey.model.UserResponse.GroupType.PRIVATE;
import static me.amarpandey.model.UserResponse.GroupType.PUBLIC;
import static me.amarpandey.model.UserResponse.MessageType.CHAT;
import static me.amarpandey.model.UserResponse.MessageType.JOIN;
import static me.amarpandey.utils.Constants.NEW_USER_JOINED;

import org.springframework.messaging.handler.annotation.DestinationVariable;
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
		UserResponse userResponse = new UserResponse(username, NEW_USER_JOINED, JOIN, PUBLIC);
		return userResponse;
	}

	@MessageMapping("/message")
	@SendTo("/topic/message")
	public UserResponse getMessage(@Payload UserResponse userResponse) {

		userResponse.setMtype(CHAT);
		userResponse.setGtype(PUBLIC);
		userResponse.setTime(valueOf(now()));

		return userResponse;
	}

	@MessageMapping("{groupid}/connect")
	@SendTo("/topic/{groupid}/connect")
	public UserResponse connect(@DestinationVariable String groupid, @RequestParam String username,
			SimpMessageHeaderAccessor headerAccessor) {
		headerAccessor.getSessionAttributes().put("username", username);
		UserResponse userResponse = new UserResponse(username, NEW_USER_JOINED, JOIN, PRIVATE);
		return userResponse;
	}

	@MessageMapping("{groupid}/message")
	@SendTo("/topic/{groupid}/message")
	public UserResponse getMessage(@DestinationVariable String groupid, @Payload UserResponse userResponse) {

		userResponse.setMtype(CHAT);
		userResponse.setGtype(PRIVATE);
		userResponse.setTime(valueOf(now()));

		return userResponse;
	}

}
