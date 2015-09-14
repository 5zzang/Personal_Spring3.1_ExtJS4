/**
 * 
 */
package com.sample.app.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.sample.app.common.BaseDao;
import com.sample.app.domain.UserInfoDomain;


/**
 * @author 5zzang
 * HomeDao
 */
@Repository
public class HomeDao extends BaseDao {
	private static final Logger logger = LoggerFactory.getLogger(HomeDao.class);
	
	/**
	 * 로그인
	 * @author 5zzang
	 * Nov 14, 2012
	 * @param userInfo
	 * @return
	 */
	public UserInfoDomain selectUserInfo(UserInfoDomain userInfo) {
		logger.info("selectUserInfo");
		
		return getSlaveSession().selectOne("com.sample.app.dao.mapper.HomeMapper.selectUserInfoByUserId", userInfo);
	}
}