package com.sample.app.common;

import java.util.Locale;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;

/**
 * 다국어를 지원하는 메시지 처리를 위한 클래스
 * @author 5zzang
 * @date 2013. 3. 5.
 */
@Component
public class MessageResource {
	@Autowired private MessageSource messageSource;
	@Autowired private RequestSession requestSession;
	
	
	/**
	 * 국제화를 지원하는 메시지를 가져옵니다.
	 * @author 5zzang
	 * @date 2013. 3. 5.
	 * @param key 메시지 키
	 * @return 키 값에 매칭되는 메시지 값
	 * String
	 */
	public String getMessage(String key) {
//		HttpServletRequest request = requestSession.getRequest();
//		Locale currentLocale = request.getLocale();
//		
//		if (currentLocale == null) {
//			currentLocale = Locale.KOREAN;
//		}
		
		Locale currentLocale = this.getCurrentLocale();
		
		try {
			return messageSource.getMessage(key, null, currentLocale);
		} catch (NoSuchMessageException e) {
			return messageSource.getMessage("error.system.message.notfound", null, currentLocale);
		}
	}
	
	/**
	 * 국제화를 지원하는 메시지를 가져옵니다. 언어를 직접 선택합니다.
	 * @author 5zzang
	 * @date 2013. 3. 5.
	 * @param key : 메시지 키
	 * @param currentLocale : 언어
	 * @return 키값에 매칭되는 메시지 값
	 * String
	 */
	public String getMessage(String key, Locale currentLocale) {
		try {
			return messageSource.getMessage(key, null, currentLocale);
		} catch (NoSuchMessageException e) {
			return messageSource.getMessage("error.system.message.notfound", null, currentLocale);
		}
	}
	
	/**
	 * 현재 locale 정보를 반환합니다.
	 * @author 5zzang
	 * @date 2013. 3. 5.
	 * @return
	 * Locale
	 */
	private Locale getCurrentLocale() {
		HttpServletRequest request = requestSession.getRequest();
		String str = ServletRequestUtils.getStringParameter(request, "language", "");
		
		if (str.length() <= 0) {
			str = "ko_kr";
		}
		
		Locale locale = null;
		if (str.indexOf("_") > -1) {
			String [] strings = str.split("_");
			locale = new Locale(strings[0], strings[1]);
		} else {
			locale = new Locale(str);
		}
		
		return locale;
	}
}