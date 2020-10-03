package com.exchange.service.app.core;

import java.time.LocalDateTime;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.exchange.service.app.pojo.Execution;
import com.exchange.service.app.pojo.Order;

public class OrderBook {
	
	@Autowired
	private MongoTemplate mongoTemplate;

	private SortedSet<Order> buySet = new TreeSet<>();
	private SortedSet<Order> sellSet = new TreeSet<>();
	
	public void addOrder(Order o) {
		switch (o.getSide()) {
		case BUY: {
			this.buySet.add(o);
			break;
		}
		case SELL: {
			this.sellSet.add(o);
			break;
		}
		default:
			System.out.println("Incorrect Side " + o.getSide());
		}
		this.matchOrders();
	}

	private void matchOrders() {
		if (this.buySet.size() > 0 && this.sellSet.size() > 0) {

			Order topBuyOrder = buySet.first();
			Order topSellOrder = sellSet.first();
			if (topBuyOrder.getPrice() >= topSellOrder.getPrice()) {
				int executionQuantity = 0;
				double executionPrice = topSellOrder.getPrice();
				if (topBuyOrder.getOpenQuantity() > topSellOrder.getOpenQuantity()) {
					executionQuantity = topSellOrder.getOpenQuantity();
					topSellOrder.setOrderStatus(Order.ORDER_STATUS_FULLY_EXECUTED);
					topBuyOrder.setOrderStatus(Order.ORDER_STATUS_PARTIALLY_EXECUTED);
					topBuyOrder.setOpenQuantity(topBuyOrder.getOpenQuantity() - executionQuantity);
					topSellOrder.setOpenQuantity(0);
					updateOrder(topSellOrder);
					updateOrder(topBuyOrder);
					createExecution(executionQuantity, executionPrice, topBuyOrder.getOrderId(), topSellOrder.getOrderId());
					sellSet.remove(topSellOrder);
				} else if (topBuyOrder.getOpenQuantity() < topSellOrder.getOpenQuantity()) {
					executionQuantity = topBuyOrder.getOpenQuantity();
					topBuyOrder.setOrderStatus(Order.ORDER_STATUS_FULLY_EXECUTED);
					topSellOrder.setOrderStatus(Order.ORDER_STATUS_PARTIALLY_EXECUTED);
					topSellOrder.setOpenQuantity(topSellOrder.getOpenQuantity() - executionQuantity);
					topBuyOrder.setOpenQuantity(0);
					updateOrder(topSellOrder);
					updateOrder(topBuyOrder);
					createExecution(executionQuantity, executionPrice, topBuyOrder.getOrderId(), topSellOrder.getOrderId());
					buySet.remove(topBuyOrder);
				} else {
					executionQuantity = topBuyOrder.getOpenQuantity();
					topBuyOrder.setOrderStatus(Order.ORDER_STATUS_FULLY_EXECUTED);
					topSellOrder.setOrderStatus(Order.ORDER_STATUS_FULLY_EXECUTED);
					topBuyOrder.setOpenQuantity(0);
					topSellOrder.setOpenQuantity(0);
					createExecution(executionQuantity, executionPrice, topBuyOrder.getOrderId(), topSellOrder.getOrderId());
					updateOrder(topSellOrder);
					updateOrder(topBuyOrder);
					buySet.remove(topBuyOrder);
					sellSet.remove(topSellOrder);
				}
				matchOrders();
			}
		}
	}
	
	private void updateOrder(Order order) {
		mongoTemplate.save(order);
	}

	private void createExecution(int executionQuantity, double executionPrice, String buyOrderId, String sellOrderId) {
		Execution e = new Execution();
		e.setExecutionId(UUID.randomUUID().toString());
		e.setPrice(executionPrice);
		e.setQuantity(executionQuantity);
		e.setBuyOrderId(buyOrderId);
		e.setSellOrderId(sellOrderId);
		e.setCreatedtime(LocalDateTime.now());
		System.out.println(e.toString());
		mongoTemplate.save(e);
		
	}

	public void printOrderBook() {
		System.out.println("BUY size : " + this.buySet.size());
		this.buySet.forEach((o) -> {
			System.out.println(o.getPrice());
		});
		System.out.println("SELL size : " + this.sellSet.size());
		this.sellSet.forEach((o) -> {
			System.out.println(o.getPrice());
		});

		//
		System.out.println("######BUY FIRST#########");
		if (this.buySet.size() > 0) {
			System.out.println(this.buySet.first());
		}
		System.out.println("######SELL FIRST#########");
		if (this.sellSet.size() > 0) {
			System.out.println(this.sellSet.first());
		}
	}
}
