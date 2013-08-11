/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, ketayao.com
 * Filename:		com.ketayao.dao.imp.CommentDaoImp.java
 * Class:			CommentDaoImp
 * Date:			2012-4-10
 * Author:			<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version          1.1.0
 * Description:		
 *
 * </pre>
 **/

package com.ketayao.dao.imp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.ketayao.dao.CommentDao;
import com.ketayao.dao.PageInfo;
import com.ketayao.pojo.Article;
import com.ketayao.pojo.Comment;

/**
 * 
 * @author <a href="mailto:ketayao@gmail.com">ketayao</a> Version 1.1.0
 * @created 2012-4-10 上午10:12:01
 */
@Repository
public class CommentDaoImp extends AbstractDaoImp<Comment> implements CommentDao {

	@Autowired
	public CommentDaoImp(JdbcTemplate jdbcTemplate) {
		this.setJdbcTemplate(jdbcTemplate);
	}

	/**
	 * @param entity
	 * @see com.ketayao.dao.GenericDao#save(java.lang.Object)
	 */
	@Override
	public void save(Comment entity) {
		this.getSimpleJdbcTemplate()
				.update("INSERT INTO lovej_comment(name,site,email,content,status,postTime,postIP,articleId) VALUES (?,?,?,?,?,?,?,?)",
						new Object[] { entity.getName(), entity.getSite(),
								entity.getEmail(), entity.getContent(),
								entity.getStatus(), entity.getPostTime(),
								entity.getPostIP(), entity.getArticleId() });
	}

	/**
	 * @param entity
	 * @see com.ketayao.dao.GenericDao#update(java.lang.Object)
	 */
	@Override
	public void update(Comment entity) {
		this.getSimpleJdbcTemplate()
				.update("UPDATE lovej_comment SET name=?,site=?,email=?,content=?,status=?,postTime=?,postIP=?,articleId=? WHERE id=?",
						new Object[] { entity.getName(), entity.getSite(),
								entity.getEmail(), entity.getContent(),
								entity.getStatus(), entity.getPostTime(),
								entity.getPostIP(), entity.getArticleId(),
								entity.getId() });
	}

	/**
	 * @param id
	 * @see com.ketayao.dao.GenericDao#delete(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) {
		this.getSimpleJdbcTemplate().update(
				"DELETE FROM lovej_comment WHERE id=" + id);
	}

	/**
	 * @return
	 * @see com.ketayao.dao.GenericDao#deleteAll()
	 */
	@Override
	public Integer deleteAll() {
		return this.getSimpleJdbcTemplate()
				.update("DELETE FROM lovej_comment ");
	}

	/**
	 * @param id
	 * @return
	 * @see com.ketayao.dao.GenericDao#get(java.lang.Integer)
	 */
	@Override
	public Comment get(Integer id) {
		List<Comment> list = this.getSimpleJdbcTemplate().query(
				"SELECT * FROM lovej_comment WHERE id=" + id,
				BeanPropertyRowMapper.newInstance(Comment.class));
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
	public List<Comment> findAll() {
		List<Comment> list = this.getSimpleJdbcTemplate().query(
				"SELECT * FROM lovej_comment",
				BeanPropertyRowMapper.newInstance(Comment.class));

		return list;
	}

	/**
	 * 
	* @param articleId
	* @param pageInfo
	* @param status
	* @return  
	* @see com.ketayao.dao.CommentDao#findPage(java.lang.Integer, com.ketayao.dao.PageInfo, java.lang.String)
	 */
	@Override
	public List<Comment> findPage(Integer articleId, PageInfo pageInfo,
			String status) {
		String sql = "SELECT * FROM lovej_comment WHERE articleId=" + articleId
				+ " AND status='" + status + "' ORDER BY postTime DESC";
		return findPage(pageInfo, sql, BeanPropertyRowMapper.newInstance(Comment.class));
	}

	/**
	 * @param pageInfo
	 * @return
	 * @see com.ketayao.dao.CommentDao#findPage(com.ketayao.dao.PageInfo)
	 */
	@Override
	public List<Comment> findPage(PageInfo pageInfo) {
		String sql = "SELECT c.*, a.title FROM lovej_comment c left join lovej_article a on c.articleId=a.id ORDER BY c.postTime DESC;";
		return findPage(pageInfo, sql, new Comment2InitArticle());
	}
	
	private class Comment2InitArticle implements RowMapper<Comment> {

		/**   
		* @param rs
		* @param rowNum
		* @return
		* @throws SQLException  
		* @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)  
		*/
		@Override
		public Comment mapRow(ResultSet rs, int rowNum) throws SQLException {
			Comment comment = new Comment();
			
			comment.setId(rs.getInt("id"));
			comment.setArticleId(rs.getInt("articleId"));
			comment.setContent(rs.getString("content"));
			comment.setEmail(rs.getString("email"));
			comment.setName(rs.getString("name"));
			comment.setPostIP(rs.getString("postIP"));
			comment.setPostTime(rs.getDate("postTime"));
			comment.setSite(rs.getString("site"));
			comment.setStatus(rs.getString("status"));
			
			Article article = new Article();
			article.setTitle(rs.getString("title"));
			comment.setArticle(article);
			
			return comment;
		}
		
	}

	/**
	 * 
	* @param pageInfo
	* @param status
	* @return  
	* @see com.ketayao.dao.CommentDao#findNewest(com.ketayao.dao.PageInfo, java.lang.String)
	 */
	@Override
	public List<Comment> findNewest(PageInfo pageInfo, String status) {
		String sql = "SELECT * FROM lovej_comment WHERE "
				+ " status='" + status + "' ORDER BY postTime DESC";
		return findPage(pageInfo, sql, BeanPropertyRowMapper.newInstance(Comment.class));
	}

}
