package com.exchange.service.app.pojo;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "executions")
public class Execution {
	
	@Id
	private String executionId;
	private int quantity;
	private double price;
	private String buyOrderId;
	private String sellOrderId;
	private LocalDateTime createdtime;
	
	
	public String getExecutionId() {
		return executionId;
	}
	public void setExecutionId(String executionId) {
		this.executionId = executionId;
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
	public String getBuyOrderId() {
		return buyOrderId;
	}
	public void setBuyOrderId(String buyOrderId) {
		this.buyOrderId = buyOrderId;
	}
	public String getSellOrderId() {
		return sellOrderId;
	}
	public void setSellOrderId(String sellOrderId) {
		this.sellOrderId = sellOrderId;
	}
	public LocalDateTime getCreatedtime() {
		return createdtime;
	}
	public void setCreatedtime(LocalDateTime createdtime) {
		this.createdtime = createdtime;
	}
	@Override
	public String toString() {
		return "Execution [executionId=" + executionId + ", quantity=" + quantity + ", price=" + price + ", buyOrderId="
				+ buyOrderId + ", sellOrderId=" + sellOrderId + "]";
	}
	
}
