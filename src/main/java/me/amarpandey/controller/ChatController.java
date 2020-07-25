package me.amarpandey.controller;

import me.amarpandey.model.ApplicationStats;
import me.amarpandey.model.Message;
import me.amarpandey.utils.ApplicationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

	@Autowired
	SimpMessagingTemplate template;

	@MessageMapping("/chat.message")
	@SendTo("/topic/public")
	public Message sendMessage(@Payload Message message) {
		// Set the message time as now before sending it back to the topic
		message.setTime(ApplicationUtils.getTime());
		return message;
	}

	@MessageMapping("/chat.user")
	@SendTo("/topic/public")
	public Message addUser(@Payload Message message, SimpMessageHeaderAccessor headerAccessor) {
		headerAccessor.getSessionAttributes().put("username", message.getUser());
		message.setTime(ApplicationUtils.getTime());
		return message;
	}

	@Scheduled(fixedDelay = 1000)
	public void sendAdhocMessage() {
		// Send the updated user count to the chat.
		template.convertAndSend("/topic/stats", ApplicationStats.userCount);
	}
}
