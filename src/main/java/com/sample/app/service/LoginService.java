package com.sample.app.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.app.dao.HomeDao;
import com.sample.app.domain.UserInfoDomain;

@Service
public class LoginService {
	private static final Logger logger = LoggerFactory.getLogger(LoginService.class);
	
	@Autowired private HomeDao homeDao;
	
	
	/**
	 * 패스워드가 맞으면 객체를 리턴한다.
	 * @author 5zzang
	 * @date 2013. 3. 13.
	 * @param userInfo
	 * @return
	 * UserInfoDomain
	 */
	public UserInfoDomain login(UserInfoDomain userInfo) {
		logger.info("login");
		
		UserInfoDomain tmpObj = homeDao.selectUserInfo(userInfo);
		
		if (userInfo.getPassword().equals(tmpObj.getPassword())) {
			return tmpObj;
		}
		
		return null;
	}
}
