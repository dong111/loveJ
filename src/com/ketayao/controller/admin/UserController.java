/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, ketayao.com
 * Filename:		com.ketayao.controller.admin.UserController.java
 * Class:			UserController
 * Date:			2012-4-6
 * Author:			<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version          1.1.0
 * Description:		
 *
 * </pre>
 **/
 
package com.ketayao.controller.admin;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ketayao.pojo.User;
import com.ketayao.service.UserService;
import com.ketayao.system.Constants;

/** 
 * 	
 * @author 	<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version  1.1.0
 * @created 2012-4-6 下午4:47:34 
 */
@Controller
@RequestMapping("/admin/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String updateBase() {
		return "admin/user/user";
	}
	
	@InitBinder
	public void userBirthdayDataBinder(WebDataBinder dataBinder) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        dataBinder.registerCustomEditor(Date.class, new CustomDateEditor(df, true)); 
	}
	
	@RequestMapping(value="/updateBase", method=RequestMethod.POST)
	public String updateBase(HttpServletRequest request, User user) {
		User loginUser = (User)request.getSession().getAttribute(Constants.LOGIN_USER);
		loginUser.setAge(user.getAge());
		loginUser.setBirthday(user.getBirthday());
		loginUser.setEmail(user.getEmail());
		loginUser.setGender(user.getGender());
		loginUser.setNickname(user.getNickname());
		loginUser.setQq(user.getQq());
		
		userService.update(loginUser);
		
		//ActionContext.getContext().getSession().put("user", loginUser);
		//addActionMessage(getText("action.user.updateBase"));
		request.setAttribute(Constants.OPERATION_SUCCESS, Constants.OPERATION_SUCCESS);
		return updateBase();
	}
}
