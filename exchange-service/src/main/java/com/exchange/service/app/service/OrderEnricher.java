package com.exchange.service.app.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.exchange.service.app.pojo.Order;

@Service
public class OrderEnricher {
	
	public void enrichOrder(Order order) {
		order.setEntryTime(LocalDateTime.now());
		order.setOrderId(UUID.randomUUID().toString());
		order.setOpenQuantity(order.getQuantity());
	}

}
