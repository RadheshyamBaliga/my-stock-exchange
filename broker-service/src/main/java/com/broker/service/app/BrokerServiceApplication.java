package com.broker.service.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import com.broker.service.app.messaging.MessageListener;
import com.broker.service.app.messaging.MessageProducer;
import com.broker.service.app.pojo.Order;
import com.broker.service.app.service.OrderService;

@SpringBootApplication
@EnableDiscoveryClient
public class BrokerServiceApplication {
	
	@Bean
	public MessageProducer messageProducer() {
		return new MessageProducer();
	}
	
	@Bean
	public MessageListener messageListener() {
		return new MessageListener();
	}

	
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(BrokerServiceApplication.class, args);
		
		 MessageProducer producer = context.getBean(MessageProducer.class);
		 
		 OrderService orderService = context.getBean(OrderService.class);
		 
		 for (int i=0; i < 100; i++) {
			 Order o = orderService.createOrder();
			 producer.sendOrderMessage(o);	 
		 }
//		 producer.sendOrderMessage(o);	 
		 
	}

}
