package com.example.lmsp;

import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Component
public class KafkaProducer {
	
	@Autowired
	private KafkaTemplate<String, String> template;	
	
	@Value("${app.kafka.topic.name}")
	private String topicName;
	
	@PostConstruct
	public void init() {
		int seed = new Random().nextInt(10, 20);
		
		for(int cc = 0; cc < seed; cc++) {
			UUID uuid = UUID.randomUUID();
			
			String message = "Message UUID = " + uuid;
			
			send(message);
		}
	}
	
	public void send(String message) {
		template.send(topicName, message).whenComplete((result, ex) -> {
			if(ex == null) {
				System.out.println("=============");
				System.out.println("Message sent");
				System.out.println("Offset: " + result.getRecordMetadata().offset());
				System.out.println("Partition: " + result.getRecordMetadata().partition());
				System.out.println("=============");
			}else {
				System.out.println("Error sending: " + ex.getMessage());
			}
		});
	}
	
	@PreDestroy
	public void close() {
		template.flush();
	}
	
}
