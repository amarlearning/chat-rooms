package me.amarpandey.controller;

import me.amarpandey.model.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

	@MessageMapping("/chat.message")
	@SendTo("/topic/public")
	public Message sendMessage(@Payload Message message) {
		return message;
	}

	@MessageMapping("/chat.user")
	@SendTo("/topic/public")
	public Message addUser(@Payload Message message, SimpMessageHeaderAccessor headerAccessor) {
		headerAccessor.getSessionAttributes().put("username", message.getUser());
		return message;
	}
}
