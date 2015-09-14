package com.sample.app.common.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeleteAdvice implements MethodInterceptor {
	private static final Logger logger = LoggerFactory.getLogger(DeleteAdvice.class);
	
	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		logger.info("invoke");
		
		Object retVal = invocation.proceed();
		System.out.println("야, 내가 해냈다!");
		
		return retVal;
	}
	
}
