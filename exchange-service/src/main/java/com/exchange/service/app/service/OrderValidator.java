package com.exchange.service.app.service;

import org.springframework.stereotype.Service;

import com.exchange.service.app.pojo.Order;
import com.exchange.service.app.pojo.ResponseMessage;
import com.exchange.service.app.pojo.Side;

@Service
public class OrderValidator {

	public ResponseMessage validateNewOrder(Order order) {
		ResponseMessage rs = new ResponseMessage();
		if (order.getIsin() == null) {
			rs.setValid(false);
			rs.setMessage("ISIN is mandatory");
			return rs;
		} else if (order.getBrokerId() == null) {
			rs.setValid(false);
			rs.setMessage("Broker ID is mandatory");
			return rs;
		} else if (order.getPrice() <= 0.0) {
			rs.setValid(false);
			rs.setMessage("Price is mandatory");
			return rs;
		} else if (order.getSide() == null
				|| !(order.getSide().equals(Side.BUY) || order.getSide().equals(Side.SELL))) {
			rs.setValid(false);
			rs.setMessage("Side is mandatory");
			return rs;
		} else if (order.getQuantity() <= 0) {
			rs.setValid(false);
			rs.setMessage("Quantity is mandatory");
			return rs;
		}
		rs.setMessage("Order Received");
		System.out.println("RS " + rs);
		return rs;

	}

}
