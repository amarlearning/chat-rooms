package me.amarpandey.config;

import static me.amarpandey.model.ChatDetails.count;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableScheduling
@Configuration
public class SchedulerConfig {

	@Autowired
	SimpMessagingTemplate template;

	@Scheduled(fixedDelay = 3000)
	public void sendAdhocMessages() {
		template.convertAndSend("/topic/stats", count);
	}
}