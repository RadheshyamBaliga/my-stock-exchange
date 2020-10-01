package com.exchange.service.app;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import com.exchange.service.app.core.OrderBook;
import com.exchange.service.app.messaging.MessageListener;
import com.exchange.service.app.messaging.MessageProducer;

@SpringBootApplication
public class ExchangeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExchangeServiceApplication.class, args);

//		context.close();
	}

}
