package com.exchange.service.app.pojo;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "orders")
public class Order implements Serializable, Comparable<Order> {

	private static final long serialVersionUID = 1L;
	
	public static final String ORDER_STATUS_OPEN = "O";
	public static final String ORDER_STATUS_FULLY_EXECUTED = "E";
	public static final String ORDER_STATUS_PARTIALLY_EXECUTED = "F";

	private String isin;
	private Side side;
	private int quantity;
	private double price;
	private String brokerId;
	private String brokerOrderId;

	@Id
	private String orderId;
	private LocalDateTime entryTime;
	private int openQuantity;
	private String orderStatus;
	
	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getBrokerId() {
		return brokerId;
	}

	public void setBrokerId(String brokerId) {
		this.brokerId = brokerId;
	}

	public String getIsin() {
		return isin;
	}

	public void setIsin(String isin) {
		this.isin = isin;
	}

	public Side getSide() {
		return side;
	}

	public void setSide(Side side) {
		this.side = side;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public LocalDateTime getEntryTime() {
		return entryTime;
	}

	public void setEntryTime(LocalDateTime entryTime) {
		this.entryTime = entryTime;
	}
	
	public String getBrokerOrderId() {
		return brokerOrderId;
	}
	public void setBrokerOrderId(String brokerOrderId) {
		this.brokerOrderId = brokerOrderId;
	}

	public int getOpenQuantity() {
		return openQuantity;
	}

	public void setOpenQuantity(int openQuantity) {
		this.openQuantity = openQuantity;
	}



	@Override
	public String toString() {
		return "Order [isin=" + isin + ", side=" + side + ", quantity=" + quantity + ", price=" + price + ", brokerId="
				+ brokerId + ", brokerOrderId=" + brokerOrderId + ", orderId=" + orderId + ", entryTime=" + entryTime
				+ ", openQuantity=" + openQuantity + "]";
	}

	@Override
	public int compareTo(Order other) {
		if (this.getSide().equals(Side.BUY)) {
			if (this.getPrice() > other.getPrice()) {
				return -1;
			} else if (this.getPrice() < other.getPrice()) {
				return 1;
			} else {
				if (this.getEntryTime().isBefore(other.getEntryTime())) {
					return -1;
				} else if (this.getEntryTime().isAfter(other.getEntryTime())) {
					return 1;
				} else {
					return 0;
				}
			}
		} else {
			if (this.getPrice() < other.getPrice()) {
				return -1;
			} else if (this.getPrice() > other.getPrice()) {
				return 1;
			} else {
				if (this.getEntryTime().isBefore(other.getEntryTime())) {
					return -1;
				} else if (this.getEntryTime().isAfter(other.getEntryTime())) {
					return 1;
				} else {
					return 0;
				}
			}
		}
	}

}
