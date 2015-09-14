package com.sample.app.common;


import java.util.List;

import static com.sample.app.common.Constant.User._USER_INFO;
import static com.sample.app.common.Constant.User._USER_AUTH;
import static com.sample.app.common.Constant.User._USER_CURRENT_MENU;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sample.app.domain.UserAuthDomain;
import com.sample.app.domain.UserInfoDomain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * HTTP 요청을처리 전후로 추가적인 작업을 할 수 있는 인터셉터 입니다.
 * preHandle과 postHandle을 이용하여 처리할 수 있습니다.
 * @author 5zzang
 * Nov 16, 2012
 */
public class RequestInterceptor extends HandlerInterceptorAdapter {
	@SuppressWarnings("unused") 
	private static final Logger logger = LoggerFactory.getLogger(RequestInterceptor.class);
	
	private RequestSession requestSession;
	
	public void setRequestSession(RequestSession requestSession) {
		this.requestSession = requestSession;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		//logger.info("preHandle");
		
		HttpSession session = request.getSession();
		//logger.info("JSESSIONID = " +session.getId());
		session.setAttribute("TIME_OUT", session.getMaxInactiveInterval()*1000);
		String location = ServletRequestUtils.getStringParameter(request, "location");
		
		UserInfoDomain userInfo = null;
		List<UserAuthDomain> authList = null;
		
		userInfo = (UserInfoDomain) session.getAttribute(_USER_INFO);
		
		// session에 유저정보가 있는지 검사. 단 index.do와 login.do는 제외.
		if (!request.getServletPath().equals("/index.do") && !request.getServletPath().equals("/login.do")) {
			if (userInfo == null) {
				response.sendRedirect("/index.do");
				return false;
			}
		}
		
		authList = (List<UserAuthDomain>) session.getAttribute(_USER_AUTH);
		
		// client로부터 받은  menuCode가 사용자의 접근 가능한 메뉴에 존재하는지 검사.
		if (authList != null) {
			boolean auth_ok = false;
			
			for (int i=0; i<authList.size(); i++) {
				UserAuthDomain auth = authList.get(i);
				
				if (auth.getMenuCode().equals(location)) {
					auth_ok = auth.getMenuCode().equals(location);
					break;
				}
			}
			
			if (auth_ok == false) {
				request.getSession().invalidate();
				response.sendRedirect("/index.do");
				return false;
			}
		}
		
		// 만약 사용자로부터 받은 현재 menuCode가 null이지 않다면, 세션에 업데이트 해준다.
		if (location != null) {
			request.getSession().setAttribute(_USER_CURRENT_MENU, location);
		}
		
		// 모든 검사가 통과되면..
		requestSession.setUserInfo(userInfo);
		requestSession.setRequest(request);
		requestSession.setResponse(response);
		
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		//logger.info("postHandle");
		
		try {
			// 항상 사용자의 현재 메뉴코드를 반환해 주어야 한다.
			request.setAttribute("location", requestSession.getRequest().getSession().getAttribute(_USER_CURRENT_MENU));
		} catch (NullPointerException e) {
			response.sendRedirect("/index.do");
		}
			
		super.postHandle(request, response, handler, modelAndView);
	}
}