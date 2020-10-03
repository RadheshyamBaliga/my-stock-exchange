package com.exchange.service.app.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.exchange.service.app.pojo.Order;

@Repository
public class OrderDAO {

	@Autowired
	private MongoTemplate mongoTemplate;

	public List<Order> findOpenAndPartiallyExecutedOrders() {
		Query query = new Query();
		List<String> cond = new ArrayList<String>();
		cond.add(Order.ORDER_STATUS_OPEN);
		cond.add(Order.ORDER_STATUS_PARTIALLY_EXECUTED);
		query.addCriteria(Criteria.where("orderStatus").in(cond));
		List<Order> orders = mongoTemplate.find(query, Order.class);
		return orders;
	}
	
	public List<Order> fetchOrders() {
		List<Order> orders = mongoTemplate.findAll(Order.class);
		return orders;
	}

}
