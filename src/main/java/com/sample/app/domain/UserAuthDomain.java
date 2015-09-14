package com.sample.app.domain;

import java.io.Serializable;

import com.google.gson.annotations.Expose;

/**
 * AuthDomain
 * @author 5zzang
 * Nov 14, 2012
 */
public class UserAuthDomain implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Expose private int 	authIdx;
	@Expose private int 	userIdx;
	@Expose private String  menuCode;
	
	
	public int getAuthIdx() {
		return authIdx;
	}
	public void setAuthIdx(int authIdx) {
		this.authIdx = authIdx;
	}
	public int getUserIdx() {
		return userIdx;
	}
	public void setUserIdx(int userIdx) {
		this.userIdx = userIdx;
	}
	public String getMenuCode() {
		return menuCode;
	}
	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}
}
