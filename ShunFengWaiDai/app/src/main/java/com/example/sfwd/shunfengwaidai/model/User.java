package com.example.sfwd.shunfengwaidai.model;

import java.io.Serializable;


public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	private String phoneNumber;
	private String userName;

	public User(Long id, String userName, String passWord, String studentId, String school) {
		this.id = id;
		this.userName = userName;
		this.passWord = passWord;
		this.studentId = studentId;
		this.school = school;
	}

	private String passWord;

	private String studentId;

	private String school;

	//省略getter settet方法、构造方法

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

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId(){
		return id;
	}
}

