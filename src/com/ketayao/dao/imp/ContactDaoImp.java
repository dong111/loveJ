/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, ketayao.com
 * Filename:		com.ketayao.dao.imp.ContactDaoImp.java
 * Class:			ContactDaoImp
 * Date:			2012-4-8
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
import org.springframework.stereotype.Repository;

import com.ketayao.dao.ContactDao;
import com.ketayao.dao.PageInfo;
import com.ketayao.pojo.Contact;

/**
 * 
 * @author <a href="mailto:ketayao@gmail.com">ketayao</a> Version 1.1.0
 * @created 2012-4-8 上午10:59:15
 */
@Repository
public class ContactDaoImp extends AbstractDaoImp<Contact> implements ContactDao {

	@Autowired
	public ContactDaoImp(JdbcTemplate jdbcTemplate) {
		this.setJdbcTemplate(jdbcTemplate);
	}

	/**
	 * @param entity
	 * @see com.ketayao.dao.GenericDao#save(java.lang.Object)
	 */
	@Override
	public void save(Contact entity) {
		this.getSimpleJdbcTemplate()
				.update("INSERT INTO lovej_contact(name,site,email,content,status,trash,postTime,postIP) VALUES (?,?,?,?,?,?,?,?)",
						new Object[] { entity.getName(), entity.getSite(),
								entity.getEmail(), entity.getContent(),
								entity.getStatus(), entity.getTrash(),
								entity.getPostTime(), entity.getPostIP() });
	}

	/**
	 * @param entity
	 * @see com.ketayao.dao.GenericDao#update(java.lang.Object)
	 */
	@Override
	public void update(Contact entity) {
		this.getSimpleJdbcTemplate()
				.update("UPDATE lovej_contact SET name=?,site=?,email=?,content=?,status=?,trash=?,postTime=?,postIP=? WHERE id=?",
						new Object[] { entity.getName(), entity.getSite(),
								entity.getEmail(), entity.getContent(),
								entity.getStatus(), entity.getTrash(),
								entity.getPostTime(), entity.getPostIP(),
								entity.getId() });
	}

	/**
	 * @param id
	 * @see com.ketayao.dao.GenericDao#delete(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) {
		this.getSimpleJdbcTemplate().update(
				"UPDATE lovej_contact SET trash=" + true + " WHERE id=" + id);
	}

	/**
	 * @return
	 * @see com.ketayao.dao.GenericDao#deleteAll()
	 */
	@Override
	public Integer deleteAll() {
		return this.getSimpleJdbcTemplate().update(
				"UPDATE lovej_contact SET trash=" + true);
	}

	/**
	 * @param id
	 * @return
	 * @see com.ketayao.dao.GenericDao#get(java.lang.Integer)
	 */
	@Override
	public Contact get(Integer id) {
		List<Contact> contacts = this.getSimpleJdbcTemplate().query(
				"SELECT * FROM lovej_contact WHERE id=" + id,
				BeanPropertyRowMapper.newInstance(Contact.class));
		if (contacts.size() > 0) {
			return contacts.get(0);
		}
		return null;
	}

	/**
	 * @return
	 * @see com.ketayao.dao.GenericDao#findAll()
	 */
	@Override
	public List<Contact> findAll() {
		List<Contact> list = this.getSimpleJdbcTemplate().query(
				"SELECT * FROM lovej_contact",
				BeanPropertyRowMapper.newInstance(Contact.class));
		return list;
	}

	/**
	 * 
	* @param pageInfo
	* @param status
	* @return  
	* @see com.ketayao.dao.ContactDao#findPage(com.ketayao.dao.PageInfo, java.lang.String)
	 */
	@Override
	public List<Contact> findPage(PageInfo pageInfo, String status) {
		String sql = "SELECT * FROM lovej_contact WHERE status='" + status
				+ "' AND trash=false order BY postTime DESC";
		return findPage(pageInfo, sql, BeanPropertyRowMapper.newInstance(Contact.class));
	}

	/**
	 * @param pageInfo
	 * @param trash
	 * @return
	 * @see com.ketayao.dao.ContactDao#findPage(com.ketayao.dao.PageInfo,
	 *      java.lang.Boolean)
	 */
	@Override
	public List<Contact> findPage(PageInfo pageInfo, Boolean trash) {
		String sql = "SELECT * FROM lovej_contact WHERE trash=" + trash
				+ " order BY postTime DESC";
		return findPage(pageInfo, sql, BeanPropertyRowMapper.newInstance(Contact.class));
	}
}
