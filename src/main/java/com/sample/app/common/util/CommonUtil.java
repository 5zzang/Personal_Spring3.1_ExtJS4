package com.sample.app.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;

/**
 * 공통적으로 사용되는 유틸리티 클래스
 * @author 5zzang
 * @date 2013. 3. 13.
 */
@Component
public class CommonUtil {
	private static final Logger logger = LoggerFactory.getLogger(CommonUtil.class);
	
	
	/**
	 * 만약 str이 Null/Empty라면 Empty("") 값을 리턴
	 * @param str
	 * @return String - 만약 str이 널 또는 빈값이면 빈값 리턴, 아니면 str 리턴
	 */
	public static String defaultString(String str) {
		return StringUtils.isEmpty(str)? "" : StringUtils.defaultString(str, "");
	}
	
	/**
	 * 만약 str이 Null/Empty라면 defaultStr 값을 리턴
	 * @param str
	 * @param defaultStr
	 * @return String - 만약 str이 널 또는 빈값이면 defaultStr 리턴, 아니면 str 리턴
	 */
	public static String defaultString(String str, String defaultStr) {
		return StringUtils.isEmpty(str)? defaultStr : StringUtils.defaultString(str, defaultStr);
	}
	
	/**
	 * 만약 Object Null/Empty라면 defaultStr 값을 리턴
	 * @param Object
	 * @return String - 만약 str이 널 또는 빈값이면 defaultStr 리턴, 아니면 str 리턴
	 */
	public static String defaultObjectString(Object obj) {
		return obj == null ? "" : ObjectUtils.defaultIfNull(obj, "").toString();
	}
	
	/**
	 * 만약 Object Null/Empty라면 defaultStr 값을 리턴
	 * @param Object
	 * @param defaultStr
	 * @return String - 만약 str이 널 또는 빈값이면 defaultStr 리턴, 아니면 str 리턴
	 */
	public static String defaultObjectString(Object obj, String defaultStr) {
		return obj == null ? defaultStr : ObjectUtils.defaultIfNull(obj, defaultStr).toString();
	}
	
	/**
	 * 만약 Object Null/Empty라면 defaultStr 값을 리턴 (HTML 태그 값 치환 )
	 * @param Object
	 * @return String - 만약 str이 널 또는 빈값이면 defaultStr 리턴, 아니면 str 리턴
	 */
	public static String defaultObjectEscapeString(Object obj) {
		return obj == null ? "" : StringEscapeUtils.escapeHtml((ObjectUtils.defaultIfNull(obj, "").toString()));
	}
	
	/**
	 * 만약 Object Null/Empty라면 defaultStr 값을 리턴 (HTML 태그 값 치환 )
	 * @param Object
	 * @param defaultStr
	 * @return String - 만약 str이 널 또는 빈값이면 defaultStr 리턴, 아니면 str 리턴
	 */
	public static String defaultObjectEscapeString(Object obj, String defaultStr) {
		return obj == null ? defaultStr : StringEscapeUtils.escapeHtml((ObjectUtils.defaultIfNull(obj, defaultStr).toString()));
	}
	
	/**
	 * int 타입 숫자를 패턴에 맞게 콤마로 구분한 문자열로 리턴한다.
	 * 패턴을 정하지 않을 경우 기본적으로 "#,##0" 이 적용
	 * @param pattern
	 * @param number
	 * @return String - 콤마로 구분된 문자값을 리턴
	 */
    public static String changeDecFormat(String pattern, int number) {
		DecimalFormat fmt = new DecimalFormat("#,##0");
	
		if (pattern != null && !"".equals(pattern.trim())) {
			fmt.applyPattern(pattern);
		}
		
		return fmt.format(new Integer(number).longValue());
	}
	
	/**
	 * long타입 숫자를 패턴에 맞게 콤마로 구분한 문자열로 리턴한다.
	 * 패턴을 정하지 않을 경우 기본적으로 "#,##0" 이 적용
	 * @param pattern
	 * @param number
	 * @return String - 콤마로 구분된 문자값을 리턴
	 */
    public static String changeDecFormat(String pattern, long number) {
		DecimalFormat fmt = new DecimalFormat("#,##0");
	
		if (pattern != null && !"".equals(pattern.trim())) {
			fmt.applyPattern(pattern);
		}
		
		return fmt.format(number);
	}
	
	/**
	 * double타입 숫자를 패턴에 맞게 콤마로 구분한 문자열로 리턴한다.
	 * 패턴을 정하지 않을 경우 기본적으로 "#,##0.00" 이 적용
	 * @param pattern
	 * @param number
	 * @return String - 콤마로 구분된 문자값을 리턴
	 */
    public static String changeDecFormat(String pattern, double number) {
		DecimalFormat fmt = new DecimalFormat("#,##0.00");
	
		if (pattern != null && !"".equals(pattern.trim())) {
			fmt.applyPattern(pattern);
		}
		
		return fmt.format(number);
	}
    
	/**
	 * java.util.Date 타입을 정해진 패턴으로 변경하여 문자열로 리턴한다.
	 * @param date java.util.Date
	 * @param pattern ex) yyyy-MM-dd HH:mm:ss.S
	 * @return String - 포맷팅된 날짜 문자열 리턴
	 */
	public static String changeDateFormat(Date date, String pattern) {
		SimpleDateFormat dateFormat =  new SimpleDateFormat(pattern);
		
		return dateFormat.format(date);
	}
	
	/**
	 * currentDatePattern으로 이루어진 문자열 날짜를 
	 * 새로운패턴(changeDatePattern)으로 변경하여 문자열로 리턴한다.
	 * @param date
	 * @param currentDatePattern
	 * @param changeDatePattern
	 * @return String - 포맷팅된 날짜 문자열 리턴
	 */
	public static String changeDateFormat(String date, String currentDatePattern, String changeDatePattern) {
		SimpleDateFormat dateFormat =  null;
		Date targetDate = null;
		
		try {
			dateFormat =  new SimpleDateFormat(currentDatePattern);
			targetDate = dateFormat.parse(date);
			dateFormat =  new SimpleDateFormat(changeDatePattern);
		} catch (ParseException e) {
			logger.error("Exception : ", e);
		} catch (Exception e) {
			logger.error("Exception : ", e);
		}
		
		return dateFormat.format(targetDate);
	}
	
	/**
	 * 구분자로 이루어진 문자열을 정해진 문자로 분리하여 배열로 리턴한다.
	 * 정규표현식을 적용하여 ^,*,$,& 등등의 특수문자 구분 가능하도록 하였다.
	 * 
	 * @param source 구분자로 이루어진 문자열
	 * @param token 구분문자
	 * @return String[] - 문자배열을 리턴
	 */
	public static String[] split(String source, char token) {
		return source == null || "".equals(source) ? null : source.split("[\\" + token + "]");
	}
	
	/**
	 * 콘솔상에서의 쉘 명령어를 실행하고, 결과를 java.lang.Process로 리턴한다.
	 * @param cmd 콘솔 쉘 명령어
	 * @param argument 명령어 인자들
	 * @return java.lang.Process - 실행결과 리턴
	 */
	public static Process commandShell(String cmd, String[] argument) {
		Process process = null;
		
		try {
			StringBuffer buf = new StringBuffer(cmd);
			
			if (argument != null && argument.length > 0) {
				for (int i=0;i<argument.length;i++) {
					buf.append(" ").append(argument[i]);
				}
				
				process = Runtime.getRuntime().exec(buf.toString());
			}
		} catch (SecurityException e) {
			logger.error("SecurityException : ", e);
		} catch (IllegalArgumentException e) {
			logger.error("IllegalArgumentException : ", e);
		} catch (NullPointerException e) {
			logger.error("NullPointerException : ", e);
		} catch (IOException e) {
			logger.error("IOException : ", e);
		} catch (Exception e) {
			logger.error("Exception : ", e);
		}
		
		return process;
	}
	
	/**
	 * 입력된 날짜값을 UnixTime으로 변환하여 반환한다.
	 * @author 5zzang
	 * @date 2013. 3. 13.
	 * @param date
	 * @return
	 * long
	 */
	public static int getUnixTime(Date date) {
		return new Long(date.getTime() / 1000).intValue();
	}
	
	/**
	 * System의 현재 날짜의 UnixTime을 반환한다.
	 * @author 5zzang
	 * @date 2013. 3. 13.
	 * @return
	 * int
	 */
	public static int getCurrentUnixTime() {
		return (int) (System.currentTimeMillis() / 1000L);
	}
	
	/**
	 * 입력된 UnixTime을 Date로 변환하여 반환.
	 * @author 5zzang
	 * @date 2013. 3. 13.
	 * @param unixTime
	 * @return
	 * Date
	 */
	public static Date getDate(int unixTime) {
		return new Date(unixTime * 1000L);
	}
	
	public static void main(String[] args) {
		System.out.println(">>>> " +getCurrentUnixTime());
	}
}