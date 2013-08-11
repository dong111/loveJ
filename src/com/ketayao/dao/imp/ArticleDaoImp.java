/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, ketayao.com
 * Filename:		com.ketayao.dao.imp.ArticleDao.java
 * Class:			ArticleDao
 * Date:			2012-4-9
 * Author:			<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version          1.1.0
 * Description:		
 *
 * </pre>
 **/

package com.ketayao.dao.imp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.ketayao.dao.PageInfo;
import com.ketayao.pojo.Article;
import com.ketayao.pojo.Category;
import com.ketayao.pojo.Comment;

/**
 * 
 * @author <a href="mailto:ketayao@gmail.com">ketayao</a> Version 1.1.0
 * @created 2012-4-9 下午12:14:06
 */
@Repository
public class ArticleDaoImp extends AbstractDaoImp<Article> implements
		com.ketayao.dao.ArticleDao {
	@Autowired
	public ArticleDaoImp(JdbcTemplate jdbcTemplate) {
		this.setJdbcTemplate(jdbcTemplate);
	}

	/**
	 * @param entity
	 * @see com.ketayao.dao.GenericDao#save(java.lang.Object)
	 */
	@Override
	public void save(Article entity) {
		this.getSimpleJdbcTemplate()
				.update("INSERT INTO lovej_article(content,modifyTime,postTime,quote,status," +
						"summary,title,view,categoryId,trash,topTime) VALUES (?,?,?,?,?,?,?,?,?,?,?)",
						new Object[] { entity.getContent(),
								entity.getModifyTime(), entity.getPostTime(),
								entity.getQuote(), entity.getStatus(),
								entity.getSummary(), entity.getTitle(),
								entity.getView(), entity.getCategoryId(),
								entity.getTrash(), entity.getTopTime() });
	}

	/**
	 * @param entity
	 * @see com.ketayao.dao.GenericDao#update(java.lang.Object)
	 */
	@Override
	public void update(Article entity) {
		this.getSimpleJdbcTemplate()
				.update("UPDATE lovej_article SET content=?,modifyTime=?,quote=?,status=?,summary=?," +
						"title=?,view=?,categoryId=?,trash=?,postTime=?,topTime=? WHERE id=?",
						new Object[] { entity.getContent(),
								entity.getModifyTime(), entity.getQuote(),
								entity.getStatus(), entity.getSummary(),
								entity.getTitle(), entity.getView(),
								entity.getCategoryId(), entity.getTrash(),
								entity.getPostTime(), entity.getTopTime(), entity.getId() });
	}

	/**
	 * @param id
	 * @see com.ketayao.dao.GenericDao#delete(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) {
		this.getSimpleJdbcTemplate().update(
				"UPDATE lovej_article SET trash=" + true + " WHERE id=" + id);
	}

	/**
	 * @return
	 * @see com.ketayao.dao.GenericDao#deleteAll()
	 */
	@Override
	public Integer deleteAll() {
		return this.getSimpleJdbcTemplate().update(
				"UPDATE lovej_article SET trash=" + true);
	}

	/**
	 * @param id
	 * @return
	 * @see com.ketayao.dao.GenericDao#get(java.lang.Integer)
	 */
	@Override
	public Article get(Integer id) {
		List<Article> list = this.getSimpleJdbcTemplate().query(
				"SELECT * FROM lovej_article WHERE id=" + id,
				BeanPropertyRowMapper.newInstance(Article.class));
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
	public List<Article> findAll() {
		List<Article> list = this.getSimpleJdbcTemplate().query(
				"SELECT * FROM lovej_article",
				BeanPropertyRowMapper.newInstance(Article.class));

		return list;
	}

	/**
	 * 
	 * @param pageInfo
	 * @param status
	 * @return
	 * @see com.ketayao.dao.ArticleDao#findPage(com.ketayao.dao.PageInfo,
	 *      java.lang.String)
	 */
	@Override
	public List<Article> findPage(PageInfo pageInfo, String status) {
		String sql = "SELECT * FROM lovej_article WHERE status='" + status
				+ "' AND trash=false order BY topTime DESC, postTime DESC";
		return findPage(pageInfo, sql,
				BeanPropertyRowMapper.newInstance(Article.class));
	}

	/**
	 * @param pageInfo
	 * @param trash
	 * @return
	 * @see com.ketayao.dao.ArticleDao#findPage(com.ketayao.dao.PageInfo,
	 *      java.lang.Boolean)
	 */
	@Override
	public List<Article> findPage(PageInfo pageInfo, Boolean trash) {
		String sql = "SELECT * FROM lovej_article WHERE trash=" + trash
				+ " order BY topTime DESC, postTime DESC";
		return findPage(pageInfo, sql,
				BeanPropertyRowMapper.newInstance(Article.class));
	}

	/**
	 * @param categoryId
	 * @param pageInfo
	 * @return
	 * @see com.ketayao.dao.ArticleDao#findPage(java.lang.Integer,
	 *      com.ketayao.dao.PageInfo)
	 */
	@Override
	public List<Article> findPage(Integer categoryId, PageInfo pageInfo) {
		String sql = "SELECT * FROM lovej_article WHERE categoryId="
				+ categoryId + " AND trash=false order BY topTime DESC, postTime DESC";
		return findPage(pageInfo, sql,
				BeanPropertyRowMapper.newInstance(Article.class));
	}

	/**
	 * @param pageInfo
	 * @return
	 * @see com.ketayao.dao.ArticleDao#findNewest(com.ketayao.dao.PageInfo)
	 */
	@Override
	public List<Article> findNewest(PageInfo pageInfo) {
		String sql = "SELECT * FROM lovej_article WHERE status='" + Article.Status.PUBLISH
				+ "' AND trash=false order BY postTime DESC";
		return findPage(pageInfo, sql,
				BeanPropertyRowMapper.newInstance(Article.class));
	}

	private class Article2InitCategory implements RowMapper<Article> {

		/**
		 * @param rs
		 * @param rowNum
		 * @return
		 * @throws SQLException
		 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet,
		 *      int)
		 */
		@Override
		public Article mapRow(ResultSet rs, int rowNum) throws SQLException {
			Article article = new Article();
			article.setId(rs.getInt("id"));
			article.setCategoryId(rs.getInt("categoryId"));
			article.setContent(rs.getString("content"));
			article.setModifyTime(rs.getDate("modifyTime"));
			article.setPostTime(rs.getTimestamp("postTime"));
			article.setQuote(rs.getString("quote"));
			article.setStatus(rs.getString("status"));
			article.setSummary(rs.getString("summary"));
			article.setTitle(rs.getString("title"));
			article.setTrash(rs.getBoolean("trash"));
			article.setView(rs.getInt("view"));
			article.setTopTime(rs.getDate("topTime"));

			// 目录
			Category category = new Category();
			category.setId(article.getCategoryId());
			category.setName(rs.getString("category_name"));
			article.setCategory(category);

			// 评论总数
			article.setCommentCount(rs.getInt("commentCount"));

			return article;
		}

	}

	/**
	 * @param pageCategoryIds
	 * @param postTime
	 * @return
	 * @see com.ketayao.dao.ArticleDao#findPreAndNext(java.util.List,
	 *      java.util.Date)
	 */
	@Override
	public List<Article> findPreAndNext(List<Integer> pageCategoryIds,
			Date postTime) {
		String ids = "";
		for (int i = 0; i < pageCategoryIds.size(); i++) {
			ids += pageCategoryIds.get(i) + ",";
		}
		ids = ids.substring(0, ids.length() - 1);

		String queryString = "SELECT * FROM lovej_article a WHERE trash=false AND a.status='"
				+ Article.Status.PUBLISH
				+ "' AND a.postTime<'"
				+ postTime
				+ "' AND "
				+ "a.categoryId IN ("
				+ ids
				+ ") ORDER BY a.postTime DESC";

		List<Article> articles = new ArrayList<Article>(2);

		PageInfo pageInfo = new PageInfo();
		pageInfo.setPageSize(1);

		List<Article> list1 = findPage(pageInfo, queryString,
				BeanPropertyRowMapper.newInstance(Article.class));
		if (list1.size() > 0) {
			articles.add(list1.get(0));
		} else {
			articles.add(null);
		}

		queryString = "SELECT * FROM lovej_article a WHERE trash=false AND a.status='"
				+ Article.Status.PUBLISH
				+ "' AND a.postTime>'"
				+ postTime
				+ "' AND "
				+ "a.categoryId IN ("
				+ ids
				+ ") ORDER BY a.postTime ASC";

		PageInfo pageInfo2 = new PageInfo();
		pageInfo2.setPageSize(1);
		list1 = findPage(pageInfo2, queryString,
				BeanPropertyRowMapper.newInstance(Article.class));
		if (list1.size() > 0) {
			articles.add(list1.get(0));
		} else {
			articles.add(null);
		}

		return articles;
	}

	/**
	 * 
	 * @param pageCategoryIds
	 * @param pageInfo
	 * @return
	 * @see com.ketayao.dao.ArticleDao#findExtendPage(java.util.List,
	 *      com.ketayao.dao.PageInfo)
	 */
	@Override
	public List<Article> findExtendPage(List<Integer> pageCategoryIds,
			PageInfo pageInfo) {
		String ids = "";
		for (int i = 0; i < pageCategoryIds.size(); i++) {
			ids += pageCategoryIds.get(i) + ",";
		}
		ids = ids.substring(0, ids.length() - 1);

		String sql = "SELECT a1.*, a2.commentCount FROM (SELECT a.*, c.name as category_name FROM lovej_article a left join lovej_category c on "
				+ "a.categoryId=c.id WHERE a.trash=false AND a.status='"
				+ Article.Status.PUBLISH
				+ "' AND c.id IN ("
				+ ids
				+ ")) a1 "
				+ " left join (select articleId,count(articleId) as commentCount from lovej_comment where status='"
				+ Comment.Status.APPROVED
				+ "' group by articleId) a2"
				+ " on a1.id=a2.articleId ORDER BY a1.topTime DESC, a1.postTime DESC";

		return findPage(pageInfo, sql, new Article2InitCategory());
	}

	/**
	 * 
	 * @param categoryId
	 * @param pageInfo
	 * @return
	 * @see com.ketayao.dao.ArticleDao#findExtendCategory(java.lang.Integer,
	 *      com.ketayao.dao.PageInfo)
	 */
	@Override
	public List<Article> findExtendCategory(Integer categoryId,
			PageInfo pageInfo) {
		String sql = "SELECT a1.*, a2.commentCount FROM (SELECT a.*, c.name as category_name FROM lovej_article a left join lovej_category c on "
				+ "a.categoryId=c.id WHERE a.trash=false AND a.status='"
				+ Article.Status.PUBLISH
				+ "' AND c.id="
				+ categoryId
				+ ") a1 "
				+ " left join (select articleId,count(articleId) as commentCount from lovej_comment where status='"
				+ Comment.Status.APPROVED
				+ "' group by articleId) a2"
				+ " on a1.id=a2.articleId ORDER BY a1.topTime DESC, a1.postTime DESC";
		return findPage(pageInfo, sql, new Article2InitCategory());
	}

	/**
	 * @param status
	 * @param q
	 * @param pageInfo
	 * @return
	 * @see com.ketayao.dao.ArticleDao#search(java.lang.String,
	 *      java.lang.String, com.ketayao.dao.PageInfo)
	 */
	@Override
	public List<Article> search(String status, String q, PageInfo pageInfo) {
		String sql = "SELECT a1.*, a2.commentCount FROM (SELECT a.*, c.name as category_name FROM lovej_article a left join lovej_category c on "
				+ "a.categoryId=c.id WHERE c.trash=false AND a.trash=false AND a.status='"
				+ status
				+ "' AND a.title like '%"
				+ q
				+ "%') a1 "
				+ " left join (SELECT articleId,COUNT(articleId) AS commentCount FROM lovej_comment WHERE status='"
				+ Comment.Status.APPROVED
				+ "' group by articleId) a2"
				+ " on a1.id=a2.articleId ORDER BY a1.topTime DESC, a1.postTime DESC";
		return findPage(pageInfo, sql, new Article2InitCategory());
	}

	/**
	 * @param pageInfo
	 * @param trash
	 * @param status
	 * @return
	 * @see com.ketayao.dao.ArticleDao#findPage(com.ketayao.dao.PageInfo,
	 *      java.lang.Boolean, java.lang.String)
	 */
	@Override
	public List<Article> findPage(PageInfo pageInfo, Boolean trash,
			String status) {
		String sql = "SELECT * FROM lovej_article WHERE trash=" + trash
				+ " AND status='" + status + "' ORDER BY topTime DESC, postTime DESC";
		return findPage(pageInfo, sql,
				BeanPropertyRowMapper.newInstance(Article.class));
	}

	/**   
	 * @param pageInfo
	 * @return  
	 * @see com.ketayao.dao.ArticleDao#findHome(com.ketayao.dao.PageInfo)  
	 */
	@Override
	public List<Article> findHome(PageInfo pageInfo) {
		String sql = "SELECT a1.*, a2.commentCount FROM (SELECT a.*, c.name as category_name FROM lovej_article a left join lovej_category c on "
				+ "a.categoryId=c.id WHERE c.trash=false AND a.trash=false AND a.status='"
				+ Article.Status.PUBLISH
				+ "') a1 "
				+ " left join (select articleId,count(articleId) as commentCount from lovej_comment where status='"
				+ Comment.Status.APPROVED
				+ "' group by articleId) a2"
				+ " on a1.id=a2.articleId ORDER BY a1.topTime DESC, a1.postTime DESC";
		return findPage(pageInfo, sql, new Article2InitCategory());
	}

}