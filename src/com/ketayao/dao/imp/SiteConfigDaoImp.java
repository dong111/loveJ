/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, ketayao.com
 * Filename:		com.ketayao.dao.imp.SiteConfigDaoImp.java
 * Class:			SiteConfigDaoImp
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

import com.ketayao.dao.SiteConfigDao;
import com.ketayao.pojo.SiteConfig;

/**
 * 
 * @author <a href="mailto:ketayao@gmail.com">ketayao</a> Version 1.1.0
 * @created 2012-4-5 下午3:48:26
 */
@Repository
public class SiteConfigDaoImp extends SimpleJdbcDaoSupport implements
		SiteConfigDao {

	@Autowired
	public SiteConfigDaoImp(JdbcTemplate jdbcTemplate) {
		this.setJdbcTemplate(jdbcTemplate);
	}

	/**
	 * @param entity
	 * @see com.ketayao.dao.GenericDao#save(java.lang.Object)
	 */
	@Override
	public void save(SiteConfig entity) {
		this.getSimpleJdbcTemplate()
				.update("INSERT INTO lovej_site_config(about,url,contactDescription,icp,name) VALUES (?,?,?,?,?)",
						new Object[] { entity.getAbout(), entity.getUrl(),
								entity.getContactDescription(),
								entity.getIcp(), entity.getName() });
	}

	/**
	 * @param entity
	 * @see com.ketayao.dao.GenericDao#update(java.lang.Object)
	 */
	@Override
	public void update(SiteConfig entity) {
		this.getSimpleJdbcTemplate()
		.update("UPDATE lovej_site_config SET about=?,url=?, contactDescription=?, icp=?, name=? WHERE id=?",
				new Object[] { entity.getAbout(), entity.getUrl(),
				entity.getContactDescription(),
				entity.getIcp(), entity.getName(), entity.getId()});
	}

	/**
	 * @param id
	 * @see com.ketayao.dao.GenericDao#delete(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) {
		this.getJdbcTemplate().update("DELETE FROM lovej_site_config WHERE id=" + id);
	}

	/**
	 * @return
	 * @see com.ketayao.dao.GenericDao#deleteAll()
	 */
	@Override
	public Integer deleteAll() {
		return this.getJdbcTemplate().update("DELETE FROM lovej_site_config ");
	}

	/**
	 * @param id
	 * @return
	 * @see com.ketayao.dao.GenericDao#get(java.lang.Integer)
	 */
	@Override
	public SiteConfig get(Integer id) {
		List<SiteConfig> siteConfigs = this.getSimpleJdbcTemplate().query("SELECT * FROM lovej_site_config WHERE id=" + id + ")", 
				BeanPropertyRowMapper.newInstance(SiteConfig.class));
		if (siteConfigs.size() > 0) {
			return siteConfigs.get(0);
		}
		return null;
	}

	/**
	 * @return
	 * @see com.ketayao.dao.GenericDao#findAll()
	 */
	@Override
	public List<SiteConfig> findAll() {
		return this.getSimpleJdbcTemplate().query("SELECT * FROM lovej_site_config",
				BeanPropertyRowMapper.newInstance(SiteConfig.class));
	}

}
