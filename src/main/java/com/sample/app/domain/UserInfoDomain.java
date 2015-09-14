package com.sample.app.domain;

import java.io.Serializable;

import com.google.gson.annotations.Expose;

/**
 * 
 * @author 5zzang
 * @date 2013. 3. 13.
 */
public class UserInfoDomain implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Expose private int  	index;
	@Expose private String 	userId;
	@Expose private String  password;
	@Expose private String  userName;
	@Expose private String  email;
	@Expose private int		status;
	@Expose private int		regDate;
	
	
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getRegDate() {
		return regDate;
	}
	public void setRegDate(int regDate) {
		this.regDate = regDate;
	}
}
