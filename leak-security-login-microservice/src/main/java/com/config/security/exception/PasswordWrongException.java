package com.config.security.exception;

import org.springframework.security.core.AuthenticationException;

public class PasswordWrongException extends AuthenticationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 284447565641137641L;

	public PasswordWrongException(String msg) {
		super(msg);
	}

}
