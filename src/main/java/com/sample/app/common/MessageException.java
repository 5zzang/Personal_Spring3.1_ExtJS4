package com.sample.app.common;

/**
 * 에러 출력을 위한 메시지 익셉션
 */
public class MessageException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	private String message;
	
	public MessageException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}