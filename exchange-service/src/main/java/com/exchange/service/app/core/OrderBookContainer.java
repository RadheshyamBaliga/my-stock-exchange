package com.exchange.service.app.core;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderBookContainer {

	@Autowired
	private ObjectFactory<OrderBook> orderBookObjectFactory;

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
