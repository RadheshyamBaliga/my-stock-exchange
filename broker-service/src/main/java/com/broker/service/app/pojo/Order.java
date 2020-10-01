package com.broker.service.app.pojo;

import java.io.Serializable;

public class Order implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	private String isin;
	private Side side;
	private int quantity;
	private double price;
	private String brokerId;
	private String brokerOrderId;
	
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
	public String getBrokerOrderId() {
		return brokerOrderId;
	}
	public void setBrokerOrderId(String brokerOrderId) {
		this.brokerOrderId = brokerOrderId;
	}
	@Override
	public String toString() {
		return "Order [isin=" + isin + ", side=" + side + ", quantity=" + quantity + ", price=" + price + ", brokerId="
				+ brokerId + ", brokerOrderId=" + brokerOrderId + "]";
	}
	
}
