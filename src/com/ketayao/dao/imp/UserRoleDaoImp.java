/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, ketayao.com
 * Filename:		com.ketayao.dao.imp.UserRoleJdbcDao.java
 * Class:			UserRoleJdbcDao
 * Date:			2012-4-5
 * Author:			<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version          1.1.0
 * Description:		
 *
 * </pre>
 **/
 
package com.ketayao.dao.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.ketayao.dao.UserRoleDao;
import com.ketayao.pojo.UserRole;

/** 
 * 	
 * @author 	<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version  1.1.0
 * @created 2012-4-5 下午3:36:30 
 */
@Repository
public class UserRoleDaoImp extends SimpleJdbcDaoSupport implements
		UserRoleDao {

	@Autowired
	public UserRoleDaoImp(JdbcTemplate jdbcTemplate) {
		this.setJdbcTemplate(jdbcTemplate);
	}
	
	/**   
	 * @param entity  
	 * @see com.ketayao.dao.GenericDao#save(java.lang.Object)  
	 */
	@Override
	public void save(UserRole entity) {
		this.getSimpleJdbcTemplate()
		.update("INSERT INTO lovej_user_role(roleId,userId) VALUES (?,?)",
				new Object[] { entity.getRole().getId(), entity.getUser().getId()});	
	}

	/**   
	 * @param entity  
	 * @see com.ketayao.dao.GenericDao#update(java.lang.Object)  
	 */
	@Override
	public void update(UserRole entity) {
		this.getSimpleJdbcTemplate()
		.update("UPDATE lovej_user_role SET roleId=?,userId=? WHERE id=?",
				new Object[] { entity.getRole().getId(), entity.getUser().getId(), entity.getId()});
	}

	/**   
	 * @param id  
	 * @see com.ketayao.dao.GenericDao#delete(java.lang.Integer)  
	 */
	@Override
	public void delete(Integer id) {
		this.getJdbcTemplate().update("DELETE FROM lovej_user_role WHERE id=" + id);
	}

	/**   
	 * @return  
	 * @see com.ketayao.dao.GenericDao#deleteAll()  
	 */
	@Override
	public Integer deleteAll() {
		return this.getJdbcTemplate().update("DELETE FROM lovej_user_role ");
	}

	/**   
	 * @param id
	 * @return  
	 * @see com.ketayao.dao.GenericDao#get(java.lang.Integer)  
	 */
	@Override
	public UserRole get(Integer id) {
		List<UserRole> userRoles = this.getSimpleJdbcTemplate().query("SELECT * FROM lovej_user_role WHERE id=" + id + ")", 
				BeanPropertyRowMapper.newInstance(UserRole.class));
		if (userRoles.size() > 0) {
			return userRoles.get(0);
		}
		return null;
	}

	/**   
	 * @return  
	 * @see com.ketayao.dao.GenericDao#findAll()  
	 */
	@Override
	public List<UserRole> findAll() {
		return this.getSimpleJdbcTemplate().query("SELECT * FROM lovej_user_role",
				BeanPropertyRowMapper.newInstance(UserRole.class));
	}

}
