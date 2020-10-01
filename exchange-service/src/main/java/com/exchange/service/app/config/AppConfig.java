package com.exchange.service.app.config;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.exchange.service.app.core.OrderBook;
import com.exchange.service.app.messaging.MessageListener;
import com.exchange.service.app.messaging.MessageProducer;

@Configuration
public class AppConfig {

	@Bean
	public MessageListener messageListener() {
		return new MessageListener();
	}
	
	@Bean
	public MessageProducer messageProducer() {
		return new MessageProducer();
	}
	
	@Bean
	@Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public OrderBook orderBook() {
		return new OrderBook();
	}
}
