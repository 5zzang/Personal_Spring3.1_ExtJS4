/*
 * Copyright Co 2006 - 2008 Wecon Communications, Inc. All right reserved.
 * 
 * Manufactured by Star Team, based on Star Framework and Star Platform.
 * You may not use this file except in compliance with the Licese.
 * You may obtain a copy of Licese at
 *
 *     http://www.weconize.com, http://starpl.com
 *
 * And if you have a question, call : 82-2-6677-4601
 */
package com.sample.app.common.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

import org.apache.commons.codec.binary.Base64;

/**
 * <pre>
 * 간단히 Triple DES Cryptography 알고리즘을 이용하여 암호화하고 복호화 하는 유틸리티
 * 클래스 main() 실행하여 키값을 생성하고, 그 값을 설정파일에 저장한다.
 * 
 * 그리고서 실제 데이터를 저장하기 전에 encrypt()로 암호화를 해서 저장하고,
 * 데이터를 꺼내올 때는 decrypt()로 복호화를 해서 사용한다.
 * </pre>
 * 
 * @author 5zzang
 * @date 2013. 3. 5.
 */
public class SimpleCryptoUtils {
	/**
	 * 예외처리 클래스 내부 클래스
	 * 
	 * @author 5zzang
	 * @date 2013. 3. 5.
	 */
	public static class SimpleCryptoException extends Exception {
		private static final long serialVersionUID = 1L;
		
		public SimpleCryptoException(String message, Throwable cause) {
			super(message, cause);
		}
	}
	
	private static final String ALGORITHM = "DESede";
	private static final String TRIPLE_DES_KEY = "206d51e9c8c168b5542a02da64c785c7da381934d5d9d557";

	/**
	 * 암호화에 사용할 키를 생성한다. 생성된 키를 프로그램의 설정 파일등에 저장해서 계속해서 사용하면 된다.
	 * 
	 * @return 자동 생성된 키를 Hex 문자열로 바꾼 값
	 * @throws SimpleCryptoException
	 */
	public static String generateHexKey() throws SimpleCryptoException {
		byte[] rawKey = null;
		
		try {
			KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
			SecretKey secretKey = keyGenerator.generateKey();
			SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(ALGORITHM);
			DESedeKeySpec desEdeSpec = (DESedeKeySpec) secretKeyFactory.getKeySpec(secretKey, javax.crypto.spec.DESedeKeySpec.class);
			rawKey = desEdeSpec.getKey();
		} catch (Exception ex) {
			throw new SimpleCryptoException("failed to generate a hex key", ex);
		}
		
		return new String(Hex.encodeHex(rawKey));
	}

	/**
	 * 암호화를 수행한다.
	 * 
	 * @param keyHex generateHexKey 메소드에 의해 생성된 Hex 문자열화 된 키
	 * @param data 암호화할 데이터 byte 배열
	 * @return 암화화 된 데이터
	 * @throws SimpleCryptoException
	 */
	public static byte[] encrypt(byte[] data) throws SimpleCryptoException {
		SecretKey key = getSecretKeyFromHex();
		byte[] encrypted = null;

		try {
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, key);
			encrypted = cipher.doFinal(data);
		} catch (Exception ex) {
			throw new SimpleCryptoException("failed to encrypt 1", ex);
		}
		
		return encrypted;
	}

	/**
	 * 문자열 데이터를 받아서 암호화 한 뒤에 그 결과를 문자열로 바꾸어서 리턴한다.
	 * 
	 * @param keyHex generateHexKey 메소드에 의해 생성된 Hex 문자열화 된 키
	 * @param data 암호화할 데이터 문자열
	 * @param encoding 문자열 인코딩
	 * @return 암호화 된 데이터를 문자열로 변환한 결과
	 * @throws SimpleCryptoException
	 */
	public static String encrypt(String data, String encoding) throws SimpleCryptoException {
		String encryptedString = null;
		
		try {
			byte[] encrypted = encrypt(data.getBytes(encoding));
			encryptedString = new String(Hex.encodeHex(encrypted));
		} catch (UnsupportedEncodingException ex) {
			throw new SimpleCryptoException("failed to encrypt 2", ex);
		}

		return encryptedString;
	}

	/**
	 * 복호화를 수행한다.
	 * 
	 * @param keyHex generateHexKey 메소드에 의해 생성된 Hex 문자열화 된 키
	 * @return 복호화된 데이터 byte 배열
	 * @throws SimpleCryptoException
	 */
	public static byte[] decrypt(byte[] data) throws SimpleCryptoException {
		SecretKey key = getSecretKeyFromHex();
		byte[] decrypted = null;

		try {
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, key);
			decrypted = cipher.doFinal(data);
		} catch (Exception ex) {
			throw new SimpleCryptoException("failed to decrypt 1", ex);
		}

		return decrypted;
	}

	/**
	 * 암호화된 문자열을 받아 복호화하여, 원본 문자열을 반환한다.
	 * 
	 * @param keyHex generateHexKey 메소드에 의해 생성된 Hex 문자열화 된 키
	 * @param data 암호화할 데이터 문자열
	 * @param encoding 문자열 인코딩
	 * @return 복호화된 문자열
	 * @throws SimpleCryptoException
	 */
	public static String decrypt(String data, String encoding) throws SimpleCryptoException {
		String decryptedString = null;
		
		try {
			byte[] unhexedData = Hex.decodeHex(data.toCharArray());
			byte[] decrypted = decrypt(unhexedData);
			decryptedString = new String(decrypted, encoding);
		} catch (UnsupportedEncodingException ex) {
			throw new SimpleCryptoException("failed to decrypt 2", ex);
		} catch (DecoderException dex) {
			throw new SimpleCryptoException("failed to decrypt 3", dex);
		}
		
		return decryptedString;
	}

	/**
	 * hex 문자열화된 키에서 SecretKey 객체를 생성한다.
	 * 
	 * @return SecretKey 객체
	 * @throws SimpleCryptoException
	 */
	private static SecretKey getSecretKeyFromHex() throws SimpleCryptoException {
		SecretKey key = null;
		
		try {
			byte[] keyBytes = Hex.decodeHex(TRIPLE_DES_KEY.toCharArray());
			key = new SecretKeySpec(keyBytes, ALGORITHM);
		} catch (DecoderException e) {
			throw new SimpleCryptoException("failed to get a secret key", e);
		}
		
		return key;
	}

	/**
	 * MD5 Hash로 암호화를 한다. 복호화는 불가능하다.
	 * 
	 * @param password 암호화할 문자열
	 * @return 암호화된 문자열
	 */
	@SuppressWarnings("static-access")
	public static String MD5Hash(String password) {
		Base64 b64 = new Base64();

		MessageDigest md;
		String md5Passwd = null;

		try {
			md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes());
			byte[] raw = md.digest();
			md5Passwd = b64.encodeBase64String(raw);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			md5Passwd = null;
		}
		
		return md5Passwd;
	}

	/**
	 * TRIPLE DES 키를 생성하여 출력한다.
	 * TEST main
	 * 
	 * @param args 명령 인자
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		// 값 생성
		String key = generateHexKey();
		System.out.println("Key : " + key);
	}
}