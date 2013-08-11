/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, ketayao.com
 * Filename:		com.ketayao.system.SystemInitServlet.java
 * Class:			SystemInitServlet
 * Date:			2011-11-14
 * Author:			<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version          1.0.0
 * Description:		
 *
 * </pre>
 **/
 
package com.ketayao.system;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.ketayao.exception.SystemInitializeException;
import com.ketayao.pojo.Role;
import com.ketayao.pojo.SiteConfig;
import com.ketayao.pojo.User;
import com.ketayao.pojo.UserRole;
import com.ketayao.service.RoleService;
import com.ketayao.service.SiteConfigService;
import com.ketayao.service.UserRoleService;
import com.ketayao.service.UserService;

/** 
 * 	
 * @author 	<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version  1.0.0
 * @created 2011-11-14 下午3:12:07 
 */

public class SystemInitServlet extends HttpServlet {
	protected final Log logger = LogFactory.getLog(getClass());
	
	/** 描述  */
	private static final long serialVersionUID = -2320798905826904304L;

	@Override
	public void init() throws ServletException {
		Properties properties = new Properties();
		InputStream inStream = null;
		try {
			// 初始化配置文件
			inStream = SystemInitServlet.class.getResourceAsStream("/loveJ.properties");
			properties.load(inStream);
			
			Set<Entry<Object, Object>> keys = properties.entrySet();
			for (Entry<Object, Object> entry : keys) {
				SystemConfig.getConfig().put(entry.getKey().toString(), entry.getValue().toString());
			}
			
			getServletContext().setAttribute(Constants.SYSTEM_CONFIG, SystemConfig.getConfig());
			
			// 初始化上传路径		
			// 文件保存目录路径
			SystemConfig.ROOT_DIR = getServletContext().getRealPath("/");

			// 初始化文件扩展类型
			SystemConfig.EXTEND_TYPE.put("image", SystemConfig.getConfig().get("lovej.upload.image.type"));
			SystemConfig.EXTEND_TYPE.put("flash", SystemConfig.getConfig().get("lovej.upload.flash.type"));
			SystemConfig.EXTEND_TYPE.put("media", SystemConfig.getConfig().get("lovej.upload.media.type"));
			SystemConfig.EXTEND_TYPE.put("file", SystemConfig.getConfig().get("lovej.upload.file.type"));
			
			
			// 初始化数据库数据
			initDatabase();
			
			logger.warn("======================system initialize success==========================");
		} catch (Exception e) {
			//e.printStackTrace();
			SystemInitializeException initializeException = 
					new SystemInitializeException("system initialize failure,not found loveJ or parse data error", e);
			logger.error(initializeException.getMessage() + ":" + e.getMessage() + "\n");
		}
	}
	
	private void initDatabase() throws Exception{
		WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
		
		RoleService roleService = wac.getBean(RoleService.class);
		List<Role> roles = roleService.findAll();
		if (roles.size() < 1) {
			Role role = new Role();
			role.setName(SystemConfig.getConfig().get("lovej.role.name"));
			role.setDescription(SystemConfig.getConfig().get("lovej.role.description"));
			role.setCreateTime(new Date());
			
			roleService.create(role);
			roles.add(role);
		}
		
		UserService userService = wac.getBean(UserService.class);
		List<User> users = userService.findAll();
		if (users.size() < 1) {
			User user = new User();

			user.setUsername(SystemConfig.getConfig().get("lovej.user.username"));
			user.setAge(Integer.parseInt(SystemConfig.getConfig().get("lovej.user.age")));
			user.setEmail(SystemConfig.getConfig().get("lovej.user.email"));
			user.setGender(Boolean.parseBoolean(SystemConfig.getConfig().get("lovej.user.sex")));
			user.setNickname(SystemConfig.getConfig().get("lovej.user.nickname"));
			user.setPassword(SystemConfig.getConfig().get("lovej.user.password"));
			user.setQq(Integer.parseInt(SystemConfig.getConfig().get("lovej.user.qq")));
			
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			user.setBirthday(format.parse(SystemConfig.getConfig().get("lovej.user.birthday")));
			
			userService.create(user);
			users.add(user);
		}
		
		UserRoleService userRoleService = wac.getBean(UserRoleService.class);
		List<UserRole> userRoles = userRoleService.findAll();
		if (userRoles == null || userRoles.size() < 1) {
			UserRole userRole = new UserRole();
			userRole.setRole(roleService.findAll().get(0));
			userRole.setUser(userService.findAll().get(0));
			
			userRoleService.create(userRole);
		}
		
		SiteConfigService siteConfigService = wac.getBean(SiteConfigService.class);
		SiteConfig siteConfig = siteConfigService.get();
		if (siteConfig == null) {
			siteConfig = new SiteConfig();
			siteConfig.setAbout(SystemConfig.getConfig().get("lovej.siteConfig.about"));
			siteConfig.setContactDescription(SystemConfig.getConfig().get("lovej.siteConfig.contact"));
			siteConfig.setIcp(SystemConfig.getConfig().get("lovej.siteConfig.icp"));
			siteConfig.setName(SystemConfig.getConfig().get("lovej.siteConfig.name"));
			siteConfig.setUrl(SystemConfig.getConfig().get("lovej.siteConfig.url"));
			
			siteConfigService.create(siteConfig);
		} 
		
		SystemConfig.setSiteConfig(siteConfig);
		getServletContext().setAttribute(Constants.SITE_CONFIG, SystemConfig.getSiteConfig());
	}
}
