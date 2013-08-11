/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, ketayao.com
 * Filename:		com.ketayao.dao.imp.RoleJdbcDao.java
 * Class:			RoleJdbcDao
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

import com.ketayao.dao.RoleDao;
import com.ketayao.pojo.Role;

/** 
 * 	
 * @author 	<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version  1.1.0
 * @created 2012-4-5 下午3:29:10 
 */
@Repository
public class RoleDaoImp extends SimpleJdbcDaoSupport implements RoleDao {

	@Autowired
	public RoleDaoImp(JdbcTemplate jdbcTemplate) {
		this.setJdbcTemplate(jdbcTemplate);
	}
	
	/**   
	 * @param entity  
	 * @see com.ketayao.dao.GenericDao#save(java.lang.Object)  
	 */
	@Override
	public void save(Role entity) {
		this.getSimpleJdbcTemplate()
		.update("INSERT INTO lovej_role(createTime,description,name) VALUES (?,?,?)",
				new Object[] { entity.getCreateTime(), entity.getDescription(),
						entity.getName()});	
	}

	/**   
	 * @param entity  
	 * @see com.ketayao.dao.GenericDao#update(java.lang.Object)  
	 */
	@Override
	public void update(Role entity) {
		this.getSimpleJdbcTemplate()
		.update("UPDATE lovej_role SET description=?,name=?,createTime=? WHERE id=?",
				new Object[] { entity.getDescription(),
						entity.getName(), entity.getCreateTime(), entity.getId()});
	}

	/**   
	 * @param id  
	 * @see com.ketayao.dao.GenericDao#delete(java.lang.Integer)  
	 */
	@Override
	public void delete(Integer id) {
		this.getJdbcTemplate().update("DELETE FROM lovej_role WHERE id=" + id);
	}

	/**   
	 * @return  
	 * @see com.ketayao.dao.GenericDao#deleteAll()  
	 */
	@Override
	public Integer deleteAll() {
		return this.getSimpleJdbcTemplate().update("DELETE FROM lovej_role ");
	}

	/**   
	 * @param id
	 * @return  
	 * @see com.ketayao.dao.GenericDao#get(java.lang.Integer)  
	 */
	@Override
	public Role get(Integer id) {
		List<Role> roles = this.getSimpleJdbcTemplate().query("SELECT * FROM lovej_role WHERE id=" + id + ")", 
				BeanPropertyRowMapper.newInstance(Role.class));
		if (roles.size() > 0) {
			return roles.get(0);
		}
		return null;
	}

	/**   
	 * @return  
	 * @see com.ketayao.dao.GenericDao#findAll()  
	 */
	@Override
	public List<Role> findAll() {
		List<Role> list = this.getSimpleJdbcTemplate().query("SELECT * FROM lovej_role",
				BeanPropertyRowMapper.newInstance(Role.class));
		return list;
	}

}
