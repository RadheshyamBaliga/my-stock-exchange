package com.broker.service.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.broker.service.app.pojo.NewOrderRequest;
import com.broker.service.app.pojo.Order;
import com.broker.service.app.pojo.ResponseMessage;
import com.broker.service.app.service.OrderService;

@RestController
public class OrderController {
	
	@Autowired
	OrderService orderService;
	
	@PostMapping("/neworder")
	public ResponseMessage onNewOrder(@RequestBody NewOrderRequest newOrderRequest) {
		System.out.println("####### Orders ##### " + newOrderRequest);
		Order o = new Order();
		o.setBrokerId("B1");
		ResponseMessage rm = new ResponseMessage();
		rm.setValid(true);
		rm.setMessage("Order placed Successfully. ID is fnwrejfhjknjgbndgvjdvndklvmndvjvd");
		System.out.println("received message");
		return rm;
	}

}
