/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, ketayao.com
 * Filename:		com.ketayao.dao.imp.LinkDaoImp.java
 * Class:			LinkDaoImp
 * Date:			2012-4-7
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

import com.ketayao.dao.LinkDao;
import com.ketayao.dao.PageInfo;
import com.ketayao.pojo.Link;

/**
 * 
 * @author <a href="mailto:ketayao@gmail.com">ketayao</a> Version 1.1.0
 * @created 2012-4-7 上午11:20:57
 */
@Repository
public class LinkDaoImp extends AbstractDaoImp<Link> implements LinkDao {

	@Autowired
	public LinkDaoImp(JdbcTemplate jdbcTemplate) {
		this.setJdbcTemplate(jdbcTemplate);
	}

	/**
	 * @param entity
	 * @see com.ketayao.dao.GenericDao#save(java.lang.Object)
	 */
	@Override
	public void save(Link entity) {
		this.getSimpleJdbcTemplate()
				.update("INSERT INTO lovej_link(createTime,description,name,site,status,trash) VALUES (?,?,?,?,?,?)",
						new Object[] { entity.getCreateTime(),
								entity.getDescription(), entity.getName(),
								entity.getSite(), entity.getStatus(), entity.getTrash() });
	}

	/**
	 * @param entity
	 * @see com.ketayao.dao.GenericDao#update(java.lang.Object)
	 */
	@Override
	public void update(Link entity) {
		this.getSimpleJdbcTemplate()
				.update("UPDATE lovej_link SET description=?,name=?,site=?,status=?,trash=?,createTime=? WHERE id=?",
						new Object[] { entity.getDescription(),
								entity.getName(), entity.getSite(),
								entity.getStatus(), entity.getTrash(), entity.getCreateTime(), entity.getId() });
	}

	/**
	 * @param id
	 * @see com.ketayao.dao.GenericDao#delete(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) {
		this.getSimpleJdbcTemplate().update(
				"UPDATE lovej_link SET trash=" + true
						+ " WHERE id=" + id);
	}

	/**
	 * @return
	 * @see com.ketayao.dao.GenericDao#deleteAll()
	 */
	@Override
	public Integer deleteAll() {
		return this.getSimpleJdbcTemplate().update(
				"UPDATE lovej_link SET trash=" + true);
	}

	/**
	 * @param id
	 * @return
	 * @see com.ketayao.dao.GenericDao#get(java.lang.Integer)
	 */
	@Override
	public Link get(Integer id) {
		List<Link> links = this.getSimpleJdbcTemplate().query(
				"SELECT * FROM lovej_link WHERE id=" + id,
				BeanPropertyRowMapper.newInstance(Link.class));
		if (links.size() > 0) {
			return links.get(0);
		}
		return null;
	}

	/**
	 * @return
	 * @see com.ketayao.dao.GenericDao#findAll()
	 */
	@Override
	public List<Link> findAll() {
		List<Link> list = this.getSimpleJdbcTemplate().query(
				"SELECT * FROM lovej_link",
				BeanPropertyRowMapper.newInstance(Link.class));
		return list;
	}

	/**   
	* @param pageInfo
	* @param trash
	* @return  
	* @see com.ketayao.dao.LinkDao#findPage(com.ketayao.dao.PageInfo, java.lang.Boolean)  
	*/
	@Override
	public List<Link> findPage(PageInfo pageInfo, Boolean trash) {
		String sql = "SELECT * FROM lovej_link WHERE trash=" + trash + " order BY createTime DESC";
		return findPage(pageInfo, sql, BeanPropertyRowMapper.newInstance(Link.class));
	}

	/**   
	* @param pageInfo
	* @param status
	* @return  
	* @see com.ketayao.dao.LinkDao#findNewest(com.ketayao.dao.PageInfo, java.lang.String)  
	*/
	@Override
	public List<Link> findNewest(PageInfo pageInfo, String status) {
		String sql = "SELECT * FROM lovej_link WHERE status='" + status
				+ "' AND trash=false order BY createTime DESC";
		return findPage(pageInfo, sql, BeanPropertyRowMapper.newInstance(Link.class));
	}

	/**   
	* @param pageInfo
	* @param trash
	* @param status
	* @return  
	* @see com.ketayao.dao.LinkDao#findPage(com.ketayao.dao.PageInfo, java.lang.Boolean, java.lang.String)  
	*/
	@Override
	public List<Link> findPage(PageInfo pageInfo, Boolean trash, String status) {
		String sql = "SELECT * FROM lovej_link WHERE status='" + status
				+ "' AND trash=" + trash + " order BY createTime DESC";
		return findPage(pageInfo, sql, BeanPropertyRowMapper.newInstance(Link.class));
	}
}
