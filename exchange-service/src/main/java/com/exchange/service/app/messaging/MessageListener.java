package com.exchange.service.app.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

import com.exchange.service.app.pojo.Order;
import com.exchange.service.app.process.MessageProcessor;

public class MessageListener {
	
	@Autowired
	MessageProcessor messageProcessor;

	@KafkaListener(topics = "${orders.topic.name}", containerFactory = "orderKafkaListenerContainerFactory")
	public void orderListener(Order order) {
		System.out.println("Received Order message: " + order.getIsin());
		messageProcessor.process(order);
	}
	
	

}