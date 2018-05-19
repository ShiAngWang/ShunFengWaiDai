package com.example.sfwd.shunfengwaidai.model;

public class Order {

	//主键Id
	private Long id;
	
	private String orderId;
	private String positionOfGet;
	private String positionTo;
	private String deadLine;
	private String reward;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getPositionOfGet() {
		return positionOfGet;
	}
	public void setPositionOfGet(String positionOfGet) {
		this.positionOfGet = positionOfGet;
	}
	public String getPositionTo() {
		return positionTo;
	}
	public void setPositionTo(String positionTo) {
		this.positionTo = positionTo;
	}
	public String getDeadLine() {
		return deadLine;
	}
	public void setDeadLine(String deadLine) {
		this.deadLine = deadLine;
	}
	public String getReward() {
		return reward;
	}
	public void setReward(String reward) {
		this.reward = reward;
	}
	
}
