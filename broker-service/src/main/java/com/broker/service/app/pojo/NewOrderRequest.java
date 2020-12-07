package com.broker.service.app.pojo;

public class NewOrderRequest {
	
	private String isincode;
	private String side;
	private String brokerOrderId;
	private int quantity;
	private double price;
	
	
	public String getIsincode() {
		return isincode;
	}
	public void setIsincode(String isincode) {
		this.isincode = isincode;
	}
	public String getSide() {
		return side;
	}
	public void setSide(String side) {
		this.side = side;
	}
	public String getBrokerOrderId() {
		return brokerOrderId;
	}
	public void setBrokerOrderId(String brokerOrderId) {
		this.brokerOrderId = brokerOrderId;
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
	@Override
	public String toString() {
		return "NewOrderRequest [isincode=" + isincode + ", side=" + side + ", brokerOrderId=" + brokerOrderId
				+ ", quantity=" + quantity + ", price=" + price + "]";
	}
	
	

}
