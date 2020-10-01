package com.exchange.service.app.process;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.exchange.service.app.core.OrderBook;
import com.exchange.service.app.core.OrderBookContainer;
import com.exchange.service.app.messaging.MessageProducer;
import com.exchange.service.app.pojo.Order;
import com.exchange.service.app.pojo.ResponseMessage;
import com.exchange.service.app.service.OrderEnricher;
import com.exchange.service.app.service.OrderValidator;

@Service
public class MessageProcessor {
	
	@Autowired
	MessageProducer messageProducer;
	
	@Autowired
	OrderValidator orderValidator;
	
	@Autowired
	OrderEnricher orderEnricher;
	
	@Autowired
	OrderBookContainer orderBookContainer;
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	public void process(Order order) {
		ResponseMessage resp = orderValidator.validateNewOrder(order);
		if (!resp.isValid()) {
			System.out.println("Sending Error Reply to broker " + order.getBrokerId());
			messageProducer.sendBrokerReplyMessage(resp, order.getBrokerId());
		} else {
			orderEnricher.enrichOrder(order);
			OrderBook ob = orderBookContainer.getOrderBook(order.getIsin());
			System.out.println("Order is Valid");
			mongoTemplate.save(order);
			ob.addOrder(order);
			ob.printOrderBook();
		}
	}

}
