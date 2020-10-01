package com.broker.service.app.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;

import com.broker.service.app.pojo.ResponseMessage;


@EnableKafka
@Configuration
public class KafkaConsumerConfig {

	@Value(value = "${kafka.bootstrap.address}")
	private String bootstrapAddress;

	public ConsumerFactory<String, ResponseMessage> responseMessageConsumerFactory() {
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
		props.put(ConsumerConfig.GROUP_ID_CONFIG, "broker-reply");
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		return new DefaultKafkaConsumerFactory<>(props);
	}


	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, ResponseMessage> responseMessageKafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, ResponseMessage> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(responseMessageConsumerFactory());
		factory.setMessageConverter(new StringJsonMessageConverter());
		return factory;
	}

}
