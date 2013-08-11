/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, ketayao.com
 * Filename:		com.ketayao.actions.admin.LoginAction.java
 * Class:			LoginAction
 * Date:			2011-11-13
 * Author:			<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version          1.0.0
 * Description:		
 *
 * </pre>
 **/

package com.ketayao.controller.admin;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ketayao.exception.NotFoundUserException;
import com.ketayao.exception.NotMatchUserPasswordException;
import com.ketayao.pojo.User;
import com.ketayao.service.UserService;
import com.ketayao.system.Constants;

/**
 * 
 * @author <a href="mailto:ketayao@gmail.com">ketayao</a> Version 1.0.0
 * @created 2011-11-13 下午12:46:22
 */
@Controller
@RequestMapping("/")
public class LoginController {
	protected final Log logger = LogFactory.getLog(getClass());

	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.getSession().removeAttribute(Constants.LOGIN_USER);

		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (Constants.COOKIE_REMEMBERME_USER_USERNAME.equals(cookie
						.getName())
						|| Constants.COOKIE_REMEMBERME_USER_PASSWORD
								.equals(cookie.getName())) {
					cookie.setValue("");
					cookie.setMaxAge(0);
					response.addCookie(cookie);
				}
			}
		}

		return login();
	}
	
	@RequestMapping(value = "/admin/index", method=RequestMethod.GET)
	public String index() {
		return "admin/index";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(HttpServletRequest request, HttpServletResponse response,  boolean rememberMe, User user, BindingResult result,
			ModelMap modelMap) {
		if (user == null || user.getUsername() == null
				|| user.getPassword() == null) {
			return login();
		}
		try {
			user = userService.login(user.getUsername(), user.getPassword());
			
			// 放入session
			request.getSession().setAttribute(Constants.LOGIN_USER, user);

			if (rememberMe) {
				Cookie cookie = new Cookie(
						Constants.COOKIE_REMEMBERME_USER_USERNAME,
						user.getUsername());
				cookie.setMaxAge(Constants.COOKIE_REMEMBERME_TIME);
				response.addCookie(cookie);

				cookie = new Cookie(Constants.COOKIE_REMEMBERME_USER_PASSWORD,
						user.getPassword());
				response.addCookie(cookie);
			}

			return "admin/index";
		} catch (NotFoundUserException e) {
			logger.error(e.getMessage());
			modelMap.addAttribute("NotFoundUserException", e.getMessage());
		} catch (NotMatchUserPasswordException e) {
			logger.error(e.getMessage());
			modelMap.addAttribute("NotMatchUserPasswordException", e.getMessage());
		} 
			
		// 清空session
		request.getSession().removeAttribute(Constants.LOGIN_USER);
		return login();
	}
}
