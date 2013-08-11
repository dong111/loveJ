/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, ketayao.com
 * Filename:		com.ketayao.dao.imp.AttachDaoImp.java
 * Class:			AttachDaoImp
 * Date:			2012-4-15
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

import com.ketayao.dao.AttachDao;
import com.ketayao.pojo.Attach;
import com.ketayao.system.SystemConfig;
import com.ketayao.util.DeleteFileUtil;

/** 
 * 	
 * @author 	<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version  1.1.0
 * @since   2012-4-15 下午2:00:22 
 */
@Repository
public class AttachDaoImp extends AbstractDaoImp<Attach> implements AttachDao {
	@Autowired
	public AttachDaoImp(JdbcTemplate jdbcTemplate) {
		this.setJdbcTemplate(jdbcTemplate);
	}

	/**   
	 * @param entity  
	 * @see com.ketayao.dao.GenericDao#save(java.lang.Object)  
	 */
	@Override
	public void save(Attach entity) {
		this.getSimpleJdbcTemplate()
		.update("INSERT INTO lovej_attach(articleId,description,url,download) VALUES (?,?,?,?)",
				new Object[] { entity.getArticleId(),
						entity.getDescription(), entity.getUrl(), entity.getDownload() });
	}

	/**   
	 * @param entity  
	 * @see com.ketayao.dao.GenericDao#update(java.lang.Object)  
	 */
	@Override
	public void update(Attach entity) {
		this.getSimpleJdbcTemplate()
		.update("UPDATE lovej_attach SET articleId=?,description=?,url=?,download=? WHERE id=?",
				new Object[] { entity.getArticleId(),
				entity.getDescription(), entity.getUrl() , entity.getDownload(),
				entity.getId() });
	}

	/**   
	 * @param id  
	 * @see com.ketayao.dao.GenericDao#delete(java.lang.Integer)  
	 */
	@Override
	public void delete(Integer id) {
		// 删除文件
		Attach attach = get(id);
		Boolean flag = DeleteFileUtil.deleteFile(SystemConfig.ROOT_DIR + 
				SystemConfig.getConfig().get("lovej.upload.file") + attach.getUrl());
		if (flag) {
			this.getSimpleJdbcTemplate().update(
					"DELETE FROM lovej_attach WHERE id=" + id);
		}		
	}

	/**   
	 * @return  
	 * @see com.ketayao.dao.GenericDao#deleteAll()  
	 */
	@Override
	public Integer deleteAll() {
		// TODO
		/**
		return this.getSimpleJdbcTemplate().update(
				"DELETE FROM lovej_attach");
		**/
		return null;
	}

	/**   
	 * @param id
	 * @return  
	 * @see com.ketayao.dao.GenericDao#get(java.lang.Integer)  
	 */
	@Override
	public Attach get(Integer id) {
		List<Attach> list = this.getSimpleJdbcTemplate().query(
				"SELECT * FROM lovej_attach WHERE id=" + id,
				BeanPropertyRowMapper.newInstance(Attach.class));
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	/**   
	 * @return  
	 * @see com.ketayao.dao.GenericDao#findAll()  
	 */
	@Override
	public List<Attach> findAll() {
		List<Attach> list = this.getSimpleJdbcTemplate().query(
				"SELECT * FROM lovej_attach",
				BeanPropertyRowMapper.newInstance(Attach.class));

		return list;
	}

	/**   
	 * @param articleId
	 * @return  
	 * @see com.ketayao.dao.AttachDao#find(java.lang.Integer)  
	 */
	@Override
	public List<Attach> find(Integer articleId) {
		List<Attach> list = this.getSimpleJdbcTemplate().query(
				"SELECT * FROM lovej_attach WHERE articleId=" + articleId,
				BeanPropertyRowMapper.newInstance(Attach.class));

		return list;
	}

}
