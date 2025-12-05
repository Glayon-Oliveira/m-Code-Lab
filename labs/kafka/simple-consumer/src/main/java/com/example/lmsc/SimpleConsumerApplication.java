package com.example.lmsc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;

import java.util.UUID;
import java.time.Instant;

@SpringBootApplication
public class SimpleConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleConsumerApplication.class, args);
	}
	
	private static final String INSTANCE_ID = UUID.randomUUID().toString();
	
	@KafkaListener(topics = {"${app.kafka.topic.name}"})
	public void listener(@Payload String message, 
		@Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
		@Header(KafkaHeaders.RECEIVED_TIMESTAMP) long ts,
		@Header(KafkaHeaders.OFFSET) long offset) {
		
		Instant timestamp = Instant.ofEpochMilli(ts);
		Instant now = Instant.now();
		
		System.out.println("INSTANCE_ID: " + INSTANCE_ID);
		System.out.println("PARTITION: " + partition);
		System.out.println("OFFSET: " + offset);
		System.out.println("TIMESTAMP: " + timestamp);
		System.out.println("NOW: " + now);
		System.out.println("MESSAGE:" + message);
		System.out.println("__------------------------------------------------------------------__");
	}

}
