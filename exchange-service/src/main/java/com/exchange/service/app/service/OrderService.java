package com.exchange.service.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exchange.service.app.dao.OrderDAO;
import com.exchange.service.app.pojo.Order;

@Service
public class OrderService {
	
	@Autowired
	OrderDAO orderDAO;
	
	public List<Order> fetchOrders() {
		return orderDAO.fetchOrders();
	}

}
