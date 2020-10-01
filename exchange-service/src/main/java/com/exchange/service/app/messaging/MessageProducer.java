package com.exchange.service.app.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.exchange.service.app.pojo.ResponseMessage;


public class MessageProducer {

	@Autowired
	private KafkaTemplate<String, ResponseMessage> responseMessageKafkaTemplate;

	@Value(value = "${broker-reply.topic.name}")
	private String brokerReplyTopicName;

	public void sendBrokerReplyMessage(ResponseMessage responseMessage, String brokerId) {
		ListenableFuture<SendResult<String, ResponseMessage>> future = responseMessageKafkaTemplate.send(brokerReplyTopicName + "-" + brokerId, responseMessage);
		future.addCallback(new ListenableFutureCallback<SendResult<String, ResponseMessage>>() {

			@Override
			public void onSuccess(SendResult<String, ResponseMessage> result) {
				System.out.println(
						"Sent message=[" + responseMessage + "] with offset=[" + result.getRecordMetadata().offset() + "]");
			}

			@Override
			public void onFailure(Throwable ex) {
				System.out.println("Unable to send message=[" + responseMessage + "] due to : " + ex.getMessage());
			}
		});
	}

}