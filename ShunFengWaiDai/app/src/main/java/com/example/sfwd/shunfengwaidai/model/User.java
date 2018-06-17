package com.example.sfwd.shunfengwaidai.model;

import java.io.Serializable;



public class User implements Serializable {
	
	
	public User() {
		super();
	}
	
	public User(String phoneNumber, String userName, String passWord, String headImage, String realname,
			String iD, String realNameState) {
		super();
		this.phoneNumber = phoneNumber;
		this.userName = userName;
		this.passWord = passWord;
		this.headImage = headImage;
		this.realname = realname;
		ID = iD;
		this.realNameState = realNameState;
	}

	private int userId;
	
	private String phoneNumber;

	private String userName;
	
	private String passWord;
	
	private String headImage;
	
	private String realname;
	
	private String ID;
	
	private String realNameState;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getHeadImage() {
		return headImage;
	}

	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getRealNameState() {
		return realNameState;
	}

	public void setRealNameState(String realNameState) {
		this.realNameState = realNameState;
	}
	

}

