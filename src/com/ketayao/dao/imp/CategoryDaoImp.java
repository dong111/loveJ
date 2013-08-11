/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, ketayao.com
 * Filename:		com.ketayao.dao.imp.CategoryDaoImp.java
 * Class:			CategoryDaoImp
 * Date:			2012-4-9
 * Author:			<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version          1.1.0
 * Description:		
 *
 * </pre>
 **/

package com.ketayao.dao.imp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ketayao.dao.CategoryDao;
import com.ketayao.pojo.Category;

/**
 * 
 * @author <a href="mailto:ketayao@gmail.com">ketayao</a> Version 1.1.0
 * @created 2012-4-9 上午11:37:47
 */
@Repository
public class CategoryDaoImp extends AbstractDaoImp<Category> implements
		CategoryDao {

	@Autowired
	public CategoryDaoImp(JdbcTemplate jdbcTemplate) {
		this.setJdbcTemplate(jdbcTemplate);
	}

	/**
	 * @param entity
	 * @see com.ketayao.dao.GenericDao#save(java.lang.Object)
	 */
	@Override
	public void save(Category entity) {
		this.getSimpleJdbcTemplate()
				.update("INSERT INTO lovej_category(type,createTime,description,name,priority,trash,parentId) VALUES (?,?,?,?,?,?,?)",
						new Object[] { entity.getType(),
								entity.getCreateTime(),
								entity.getDescription(), entity.getName(),
								entity.getPriority(), entity.getTrash(),
								entity.getParentId() });
	}

	/**
	 * @param entity
	 * @see com.ketayao.dao.GenericDao#update(java.lang.Object)
	 */
	@Override
	public void update(Category entity) {
		this.getSimpleJdbcTemplate()
				.update("UPDATE lovej_category SET type=?,description=?,name=?,priority=?,trash=?,parentId=?,createTime=? WHERE id=?",
						new Object[] { entity.getType(),
								entity.getDescription(), entity.getName(),
								entity.getPriority(), entity.getTrash(),
								entity.getParentId(), entity.getCreateTime(),
								entity.getId() });
	}

	/**
	 * @param id
	 * @see com.ketayao.dao.GenericDao#delete(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) {
		this.getSimpleJdbcTemplate().update(
				"UPDATE lovej_category SET trash=" + true + " WHERE id=" + id);
	}

	/**
	 * @return
	 * @see com.ketayao.dao.GenericDao#deleteAll()
	 */
	@Override
	public Integer deleteAll() {
		return this.getSimpleJdbcTemplate().update(
				"UPDATE lovej_category SET trash=" + true);
	}

	/**
	 * @param id
	 * @return
	 * @see com.ketayao.dao.GenericDao#get(java.lang.Integer)
	 */
	@Override
	public Category get(Integer id) {
		List<Category> list = this.getSimpleJdbcTemplate().query(
				"SELECT * FROM lovej_category WHERE id=" + id,
				BeanPropertyRowMapper.newInstance(Category.class));
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
	public List<Category> findAll() {
		List<Category> list = this.getSimpleJdbcTemplate().query(
				"SELECT * FROM lovej_category",
				BeanPropertyRowMapper.newInstance(Category.class));

		return list;
	}

	/**
	 * @param name
	 * @return
	 * @see com.ketayao.dao.CategoryDao#getCategory(java.lang.String)
	 */
	@Override
	public Category getCategory(String name) {
		List<Category> list = this.getSimpleJdbcTemplate().query(
				"SELECT * FROM lovej_category WHERE name='" + name + "'",
				BeanPropertyRowMapper.newInstance(Category.class));
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	/**
	 * 
	 * @param trash
	 * @return
	 * @see com.ketayao.dao.CategoryDao#findParent(java.lang.Boolean)
	 */
	@Override
	public List<Category> findParent(Boolean trash) {
		List<Category> list = this.getSimpleJdbcTemplate().query(
				"SELECT * FROM lovej_category WHERE parentId IS NULL AND trash="
						+ trash,
				BeanPropertyRowMapper.newInstance(Category.class));

		Collections.sort(list, new CategorySort());

		return list;
	}

	/**
	 * category排序类
	 * 
	 * @author <a href="mailto:ketayao@gmail.com">ketayao</a> Version 1.0.0
	 * @created 2011-11-25 下午2:02:08
	 */
	private class CategorySort implements Comparator<Category> {

		/**
		 * @param o1
		 * @param o2
		 * @return
		 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
		 */
		@Override
		public int compare(Category o1, Category o2) {
			if (o1 == o2) {
				return 0;
			}

			if (o1.getPriority().intValue() == o2.getPriority().intValue()) {
				if (o1.getId() > o2.getId()) {
					return 1;
				}
			} else {
				if (o1.getPriority() > o2.getPriority()) {
					return 1;
				}
			}

			return -1;
		}
	}

	/**
	 * @param trash
	 * @return
	 * @see com.ketayao.dao.CategoryDao#findTree(java.lang.Boolean)
	 */
	@Override
	public List<Category> findTree(Boolean trash) {
		List<Category> list = this.getSimpleJdbcTemplate().query(
				"SELECT * FROM lovej_category WHERE trash=" + trash,
				BeanPropertyRowMapper.newInstance(Category.class));

		List<Category> parents = new ArrayList<Category>();
		List<Category> children = new ArrayList<Category>();
		for (Iterator<Category> iterator = list.iterator(); iterator.hasNext();) {
			Category category = iterator.next();
			if (category.getParentId() == null) {
				parents.add(category);
			} else {
				children.add(category);
			}
		}

		for (Iterator<Category> iterator = parents.iterator(); iterator
				.hasNext();) {
			Category p = iterator.next();
			for (Iterator<Category> iterator2 = children.iterator(); iterator2
					.hasNext();) {
				Category c = iterator2.next();
				if (p.getId().intValue() == c.getParentId().intValue()) {
					p.getChildren().add(c);
				}
			}
			Collections.sort(p.getChildren(), new CategorySort());
		}
		Collections.sort(parents, new CategorySort());

		return parents;
	}
}
