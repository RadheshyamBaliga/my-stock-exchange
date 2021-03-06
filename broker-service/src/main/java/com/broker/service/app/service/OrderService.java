package com.broker.service.app.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.broker.service.app.pojo.Order;
import com.broker.service.app.pojo.Side;

@Service
public class OrderService {
	
	@Value(value = "${broker.id}")
	private String brokerId;
	
	public Order createOrder() {
		
		Order o = new Order();
		 o.setIsin("DE0006675788");
		 o.setPrice(10.5);
		 o.setQuantity(250);
		 double r = Math.random();
			if (r >= 0.5) {
				o.setSide(Side.SELL);
			} else {
				o.setSide(Side.BUY);		
			}
		 
		 o.setBrokerId(brokerId);
		 o.setBrokerOrderId(UUID.randomUUID().toString());
		 return o;
	}

}
