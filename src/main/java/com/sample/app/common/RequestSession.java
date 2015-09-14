package com.sample.app.common;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;

import com.sample.app.domain.UserInfoDomain;

/**
 * 사용자의 1회의 요청에 대한 세션 저장소
 * 필요한 모든 데이터를 이곳에서 보관하시면 한번에 Request에 대하여 보존이 됩니다.
 */
@Component
public class RequestSession implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private UserInfoDomain userInfo;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession session;


	public HttpServletRequest getRequest() {
		return request;
	}
	
	public void setRequest(HttpServletRequest request) {
		if ((session = request.getSession(false)) == null) {
			session = request.getSession(true);
		}

		this.request = request;
	}
	
	public HttpServletResponse getResponse() {
		return response;
	}
	
	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}
	public HttpSession getSession() {
		return session;
	}
	public UserInfoDomain getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(UserInfoDomain userInfo) {
		this.userInfo = userInfo;
	}
}