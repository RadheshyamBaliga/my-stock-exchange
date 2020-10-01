package com.broker.service.app.messaging;

import org.springframework.kafka.annotation.KafkaListener;

import com.broker.service.app.pojo.ResponseMessage;

public class MessageListener {
	
	@KafkaListener(topics = "${broker-reply.topic.name}", containerFactory = "responseMessageKafkaListenerContainerFactory")
	public void orderListener(ResponseMessage respMsg) {
		System.out.println("Received ResponseMessage : " + respMsg);
	}
	
	

}