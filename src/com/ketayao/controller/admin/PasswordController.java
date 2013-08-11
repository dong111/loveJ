/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, ketayao.com
 * Filename:		com.ketayao.controller.admin.PasswordController.java
 * Class:			PasswordController
 * Date:			2012-4-6
 * Author:			<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version          1.1.0
 * Description:		
 *
 * </pre>
 **/
 
package com.ketayao.controller.admin;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ketayao.pojo.User;
import com.ketayao.service.UserService;
import com.ketayao.system.Constants;
import com.ketayao.util.SimpleEncrypt;

/** 
 * 	
 * @author 	<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version  1.1.0
 * @created 2012-4-6 下午9:12:25 
 */
@Controller
@RequestMapping("/admin/user")
public class PasswordController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/updatePassword", method=RequestMethod.GET)
	public String updatePassword() {
		return "admin/user/password";
	}
	
	@RequestMapping(value="/updatePassword", method=RequestMethod.POST)
	public String updatePassword(HttpServletRequest request, String oldPassword, String newPassword) {
		User user = (User)request.getSession().getAttribute(Constants.LOGIN_USER);
		
		oldPassword = SimpleEncrypt.encrypt(oldPassword);
		
		if (oldPassword.equals(user.getPassword())) {
			newPassword = SimpleEncrypt.encrypt(newPassword);
			user.setPassword(newPassword);
			userService.update(user);
			
			//ActionContext.getContext().getSession().put("user", user);
			//addActionMessage(getText("action.user.updatePassword"));
			request.setAttribute(Constants.OPERATION_SUCCESS, Constants.OPERATION_SUCCESS);
			return updatePassword();
		}
		
		request.setAttribute("notsame", Constants.OPERATION_FAILURE);
		return updatePassword();
	}
}
