package com.broker.service.app.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.broker.service.app.pojo.Order;

public class MessageProducer {

	@Autowired
	private KafkaTemplate<String, Order> orderKafkaTemplate;

	@Value(value = "${orders.topic.name}")
	private String ordersTopicName;

	public void sendOrderMessage(Order order) {
		ListenableFuture<SendResult<String, Order>> future = orderKafkaTemplate.send(ordersTopicName, order);
		future.addCallback(new ListenableFutureCallback<SendResult<String, Order>>() {

			@Override
			public void onSuccess(SendResult<String, Order> result) {
				System.out.println(
						"Sent message=[" + order + "] with offset=[" + result.getRecordMetadata().offset() + "]");
			}

			@Override
			public void onFailure(Throwable ex) {
				System.out.println("Unable to send message=[" + order + "] due to : " + ex.getMessage());
			}
		});
	}

}