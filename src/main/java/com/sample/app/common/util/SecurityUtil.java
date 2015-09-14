/*
 * @(#)SecurityUtil.java
 * Date : 2009. 8. 5.
 * Copyright: (C) 2010 by NICSTECH co.,Ltd. All right reserved.
 */

package com.sample.app.common.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 보안에 사용되는 유틸리티 클래스
 * @author 5zzang
 * @date 2013. 3. 13.
 */
@Component
public class SecurityUtil {
	private static final Logger logger = LoggerFactory.getLogger(SecurityUtil.class);

	
	/**
	 * MD5 메세지 축약 방식의 해시문자열을 리턴한다.
	 * @author 5zzang
	 * @date 2013. 3. 13.
	 * @param message
	 * @return
	 * String
	 */
	public static String messageToMD5Hash(String message) {
		String sHash = null;

		try {
			byte[] md5bytes = messageDigest(message.getBytes(), "MD5");
			sHash = toHex(md5bytes);
		} catch (Exception e) {
			logger.error("Exception : ", e);
	    }

    	return sHash;
	}
	
	/**
	 * MD5 메세지 축약 방식의 Base64 문자열을 리턴한다.
	 * @author 5zzang
	 * @date 2013. 3. 13.
	 * @param message
	 * @return
	 * String
	 */
	public static String messageToMD5Base64(String message) {
		StringBuffer hashedpasswd = new StringBuffer("");

		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(message.getBytes());
			byte[] raw = md.digest();
			hashedpasswd.append(Base64.encodeBase64String(raw));
		} catch (Exception e) {
			logger.error("Exception : ", e);
	    }

    	return hashedpasswd.toString();
	}

	/**
	 * SHA256 메세지 축약 방식의 해시문자열을 리턴한다.
	 * @author 5zzang
	 * @date 2013. 3. 13.
	 * @param message
	 * @return
	 * String
	 */
	public static String messageToSHA256Hash(String message) {
		String sHash = null;

		try {
			byte[] sha256bytes = messageDigest(message.getBytes(), "SHA-256");
			sHash = toHex(sha256bytes);
		} catch (Exception e) {
			logger.error("Exception : ", e);
	    }

    	return sHash;
	}
	
	/**
	 * SHA256 메세지 축약 방식의 Base64 문자열을 리턴한다.
	 * @author 5zzang
	 * @date 2013. 3. 13.
	 * @param message
	 * @return
	 * String
	 */
	public static String messageToSHA256Base64(String message) {
		StringBuffer hashedpasswd = new StringBuffer("");

		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(message.getBytes());
			byte[] raw = md.digest();
			hashedpasswd.append(Base64.encodeBase64String(raw));
		} catch (Exception e) {
			logger.error("Exception : ", e);
	    }

    	return hashedpasswd.toString();
	}
	
	/**
	 * SHA1 메세지 축약 방식의 해시문자열을 리턴한다.
	 * @author 5zzang
	 * @date 2013. 3. 13.
	 * @param message
	 * @return
	 * String
	 */
	public static String messageToSHA1Hash(String message) {
		String sHash = null;

		try {
			byte[] shabytes = messageDigest(message.getBytes(), "SHA");
			sHash = toHex(shabytes);
		} catch (Exception e) {
			logger.error("Exception : ", e);
	    }

    	return sHash;
	}

	/**
	 * SHA1 메세지 축약 방식의 Base64 문자열을 리턴한다.
	 * @author 5zzang
	 * @date 2013. 3. 13.
	 * @param message
	 * @return
	 * String
	 */
	public static String messageToSHA1Base64(String message) {
		StringBuffer hashedpasswd = new StringBuffer("");

		try {
			MessageDigest md = MessageDigest.getInstance("SHA");
			md.update(message.getBytes());
			byte[] raw = md.digest();
			hashedpasswd.append(Base64.encodeBase64String(raw));
		} catch (Exception e) {
			logger.error("Exception : ", e);
	    }

    	return hashedpasswd.toString();
	}

	private static byte[] messageDigest(byte[] message, String algorithm) {
		MessageDigest messageDigest = null;

		try {
			messageDigest = MessageDigest.getInstance(algorithm) ;
			messageDigest.update(message);
		} catch (NoSuchAlgorithmException e) {
			logger.error("NoSuchAlgorithmException : ", e);
	    } catch (Exception e) {
			logger.error("Exception : ", e);
	    }

	    return messageDigest.digest();
	}

	private static String toHex(byte[] digest) {
		StringBuffer hashedpasswd = new StringBuffer("");
		String hex = null;

    	for (int i=0;i<digest.length;i++){
    		hex =  Integer.toHexString(0xFF & digest[i]);

    		if(hex.length() == 1) {
    			hashedpasswd.append('0');
    		}

    		hashedpasswd.append(hex);
    	}

    	return hashedpasswd.toString();
	}
}
