package com.example.sfwd.shunfengwaidai.model;

public class Express extends Order {
	private String numberTail;//手机尾号
	private String expressInfo;//快递信息
	
	
	public String getNumberTail() {
		return numberTail;
	}
	public void setNumberTail(String numberTail) {
		this.numberTail = numberTail;
	}
	public String getExpressInfo() {
		return expressInfo;
	}
	public void setExpressInfo(String expressInfo) {
		this.expressInfo = expressInfo;
	}
	
}
