package com.example.lmsc.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
@EnableKafka
public class KafkaConfig {

	@Bean
	public NewTopic oneTopic(@Value("${app.kafka.topic.name}") String name,
		@Value("${app.kafka.topic.partitions}") int partitions,
		@Value("${app.kafka.topic.replication}") int replication) {
		
		return TopicBuilder.name(name)
				.partitions(partitions)
				.replicas(replication)
				.build();
	}
	
}
