package com.exchange.service.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exchange.service.app.pojo.Order;
import com.exchange.service.app.service.OrderService;

@RestController
public class OrderController {
	
	@Autowired
	OrderService orderService;
	
	@GetMapping("/orders")
	public List<Order> orders() {
		return orderService.fetchOrders();
	}

}
