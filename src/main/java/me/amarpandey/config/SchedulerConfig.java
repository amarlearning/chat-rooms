package me.amarpandey.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@Configuration
public class SchedulerConfig {

	@Autowired
	SimpMessagingTemplate template;

	/*@Scheduled(fixedDelay = 3000)
	public void sendAdhocMessages() {
		template.convertAndSend("/topic/user", new UserResponse("Fixed Delay Scheduler"));
	}*/
}