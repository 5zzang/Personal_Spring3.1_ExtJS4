package com.sample.app.common.aop;

import java.util.HashMap;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sample.app.common.RequestSession;

@Component
public class SelectAdvice implements MethodInterceptor {
	private static final Logger logger = LoggerFactory.getLogger(SelectAdvice.class);
	
	@Autowired public RequestSession requestSession;
	
	
	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		HashMap<String, Object> params = new HashMap<String, Object>();
		
		
		System.out.println("arguments : " +gson.toJson(invocation.getArguments()));
		
		Object retVal = invocation.proceed();

		//System.out.println("userId : " +requestSession.getUserInfo().getUserId());
		System.out.println("retVal : " +gson.toJson(retVal));
		
		// SELECT가 실행되고 난 뒤.
		params.put("logText", gson.toJson(retVal));
		
		
		return retVal;
	}
	
}
