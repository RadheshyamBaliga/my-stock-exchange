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
		 o.setPrice(10.3);
		 o.setQuantity(0);
		 o.setSide(Side.BUY);
		 o.setBrokerId(brokerId);
		 o.setBrokerOrderId(UUID.randomUUID().toString());
		 return o;
	}

}
