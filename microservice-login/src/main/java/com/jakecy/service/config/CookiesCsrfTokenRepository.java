package com.jakecy.service.config;


import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.DefaultCsrfToken;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * Created by symark on 16/4/27.
 */
public class CookiesCsrfTokenRepository implements CsrfTokenRepository {

	private static final String DEFAULT_CSRF_PARAMETER_NAME = "_csrf";

	private static final String DEFAULT_CSRF_HEADER_NAME = "X-CSRF-TOKEN";

	private static final String DEFAULT_CSRF_TOKEN_ATTR_NAME = CookiesCsrfTokenRepository.class.getName()
			.concat(".CSRF_TOKEN");

	private String parameterName = DEFAULT_CSRF_PARAMETER_NAME;

	private String headerName = DEFAULT_CSRF_HEADER_NAME;

	private String sessionAttributeName = DEFAULT_CSRF_TOKEN_ATTR_NAME;

	@Override
	public CsrfToken generateToken(HttpServletRequest request) {
		return new DefaultCsrfToken(headerName, parameterName, createNewToken());

	}

	private String createNewToken() {
		return UUID.randomUUID().toString();
	}

	@Override
	public void saveToken(CsrfToken token, HttpServletRequest request, HttpServletResponse response) {
		if (token == null) {
			try {
				deleteCokie(request, response, sessionAttributeName);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				writeCokie(response, sessionAttributeName, token.getToken(), 1);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public CsrfToken loadToken(HttpServletRequest request) {
		try {
			String token = readCokie(request, sessionAttributeName);
			if (token != null) {
				return new DefaultCsrfToken(headerName, parameterName, token);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private String readCokie(HttpServletRequest request, String name) {
		String value = null;
		if (name != null) {
			Cookie[] cookies = request.getCookies();
			if (cookies != null && cookies.length >= 2) {
				for (int i = 0; i < cookies.length; i++) {
					Cookie cookie = cookies[i];
					if (name.equals(cookie.getName())) {
						value = cookie.getValue();
					}
				}
			}
		}
		return value;
	}

	protected void writeCokie(HttpServletResponse response, String name, String value, int days) {
		int day = 24 * 60 * 60;
		Cookie cookie = new Cookie(name, value);
		cookie.setMaxAge(days * day);
		cookie.setSecure(cookie.getSecure());
		response.addCookie(cookie);
	}

	private void deleteCokie(HttpServletRequest request, HttpServletResponse response, String name) {
		Cookie[] cookies = request.getCookies();
		for (int i = 0; i < cookies.length; i++) {
			Cookie cookie = cookies[i];
			if (cookie.getName().equals(name)) {
				cookie.setMaxAge(0);
				response.addCookie(cookie);
			}
		}
	}

}
