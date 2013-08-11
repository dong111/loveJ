/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, ketayao.com
 * Filename:		com.ketayao.controller.admin.CategoryController.java
 * Class:			CategoryController
 * Date:			2012-4-9
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
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ketayao.pojo.Category;
import com.ketayao.service.CategoryService;
import com.ketayao.system.Constants;

/** 
 * 	
 * @author 	<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version  1.1.0
 * @created 2012-4-9 下午12:38:43 
 */
@Controller
@RequestMapping("/admin/content")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	private static final String CREATE = "admin/content/category-create";
	private static final String READ = "admin/content/category-read";
	private static final String UPDATE = "admin/content/category-update";
	private static final String VIEW = "admin/content/category-view";
	
	@InitBinder
	public void userBirthdayDataBinder(WebDataBinder dataBinder) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        dataBinder.registerCustomEditor(Date.class, new CustomDateEditor(df, true)); 
	}
	
	@RequestMapping(value="/readCategory", method=RequestMethod.GET)
	public String read(Map<String, Object> map) {
		List<Category> parents = categoryService.findTree(false);
		map.put("parents", parents);
		return READ;
	}
	
	@RequestMapping(value="/createCategory", method=RequestMethod.GET)
	public String preCreate(Map<String, Object> map) {
		List<Category> parents = categoryService.findParent(false);
		map.put("parents", parents);
		return CREATE;
	}
	
	@RequestMapping(value="/createCategory", method=RequestMethod.POST)
	public String create(Map<String, Object> map, Category category) {
		category.setCreateTime(new Date());
		categoryService.create(category);
		
		map.put(Constants.OPERATION_SUCCESS, Constants.OPERATION_SUCCESS);
		return preCreate(map);
	}
	
	@RequestMapping(value="/deleteCategory", method=RequestMethod.GET)
	public String delete(Map<String, Object> map, Integer id) {
		categoryService.delete(id);
		//addActionMessage(getText("action.category.delete"));
		return read(map);
	}
	
	@RequestMapping(value="/viewCategory", method=RequestMethod.GET)
	public String view(Map<String, Object> map, Integer id) {
		Category category = categoryService.getCategory(id);
		map.put("category", category);
		return VIEW;
	}
	
	@RequestMapping(value="/updateCategory", method=RequestMethod.GET)
	public String preUpdate(Map<String, Object> map, Integer id) {		
		Category category = categoryService.getCategory(id);
		map.put("category", category);
		return UPDATE;
	}
	
	@RequestMapping(value="/updateCategory", method=RequestMethod.POST)
	public String update(Map<String, Object> map, Category category) {
		categoryService.update(category);
		//addActionMessage(getText("action.category.update"));
		map.put(Constants.OPERATION_SUCCESS, Constants.OPERATION_SUCCESS);
		map.put("category", category);
		return UPDATE;
	}
}
