/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, ketayao.com
 * Filename:		com.ketayao.interceptor.AuthorizeInterceptor.java
 * Class:			AuthorizeInterceptor
 * Date:			2012-4-11
 * Author:			<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version          1.1.0
 * Description:		
 *
 * </pre>
 **/

package com.ketayao.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ketayao.pojo.User;
import com.ketayao.service.UserService;
import com.ketayao.system.Constants;

/**
 * 
 * @author <a href="mailto:ketayao@gmail.com">ketayao</a> Version 1.1.0
 * @created 2012-4-11 下午1:07:07
 */

public class AuthorizeInterceptor extends HandlerInterceptorAdapter {

	private UserService userService;

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		User user = (User) request.getSession().getAttribute(
				Constants.LOGIN_USER);

		if (user != null) {
			return true;
		}

		Cookie[] cookies = request.getCookies();
		user = new User();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (Constants.COOKIE_REMEMBERME_USER_USERNAME.equals(cookie
						.getName())) {
					user.setUsername(cookie.getValue());
				}
				if (Constants.COOKIE_REMEMBERME_USER_PASSWORD.equals(cookie
						.getName())) {
					user.setPassword(cookie.getValue());
				}
			}

			if (user.getUsername() != null && user.getPassword() != null) {
				try {
					user = userService.login(user.getUsername(), user.getPassword());
				} catch (Exception e) {
					response.sendRedirect(request.getContextPath() + "/login");
					return false;
				}
				if (user != null) {
					request.getSession().setAttribute(Constants.LOGIN_USER, user);
					return true;
				}

			}
		}

		response.sendRedirect(request.getContextPath() + "/login");
		return false;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
