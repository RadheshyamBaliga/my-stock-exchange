package com.exchange.service.app.core;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.exchange.service.app.dao.OrderDAO;
import com.exchange.service.app.pojo.Order;

@Service
public class OrderBookContainer {

	@Autowired
	private ObjectFactory<OrderBook> orderBookObjectFactory;
	
	@Autowired
	private OrderDAO orderDAO;
	
	@PostConstruct
	public void loadOrderBook() {
		List<Order> orders = orderDAO.findOpenAndPartiallyExecutedOrders();
		System.out.println("OB Post Contruct " + orders.size());
		orders.forEach(order -> {
			OrderBook ob = getOrderBook(order.getIsin());
			ob.addOrder(order);
			System.out.println("Successfully added Order to OrderBook during Startup " + order);
		});
		
	}

	public OrderBook getOrderBookInstance() {
		return orderBookObjectFactory.getObject();
	}

	private Map<String, OrderBook> obMap = new HashMap<String, OrderBook>();

	public OrderBook getOrderBook(String isin) {
		OrderBook ob = obMap.get(isin);
		if (ob == null) {
			synchronized (OrderBookContainer.class) {
				if (ob == null) {
					ob = getOrderBookInstance();
					this.obMap.put(isin, ob);
				}
			}
		}
		return ob;
	}

}
