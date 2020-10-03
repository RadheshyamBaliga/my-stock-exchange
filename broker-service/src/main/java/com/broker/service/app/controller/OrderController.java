package com.broker.service.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.broker.service.app.pojo.Order;
import com.broker.service.app.service.OrderService;

@RestController
public class OrderController {
	
	@Autowired
	OrderService orderService;
	
	@GetMapping("/orders")
	public Order onOrder() {
		System.out.println("####### Orders #####");
		Order o = new Order();
		o.setBrokerId("B1");
		return o;
	}

}
