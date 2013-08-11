/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, ketayao.com
 * Filename:		com.ketayao.dao.imp.UserJdbcDaoImp.java
 * Class:			UserJdbcDaoImp
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

import com.ketayao.dao.UserDao;
import com.ketayao.exception.NotFoundUserException;
import com.ketayao.exception.NotMatchUserPasswordException;
import com.ketayao.pojo.User;
import com.ketayao.util.SimpleEncrypt;

/**
 * 
 * @author <a href="mailto:ketayao@gmail.com">ketayao</a> Version 1.1.0
 * @created 2012-4-5 下午2:05:59
 */
@Repository
public class UserDaoImp extends SimpleJdbcDaoSupport implements UserDao {

	@Autowired
	public UserDaoImp(JdbcTemplate jdbcTemplate) {
		this.setJdbcTemplate(jdbcTemplate);
	}

	/**
	 * @param entity
	 * @see com.ketayao.dao.GenericDao#save(java.lang.Object)
	 */
	@Override
	public void save(User entity) {
		this.getSimpleJdbcTemplate()
				.update("INSERT INTO lovej_user(age,birthday,email,gender,nickname,password,qq,username) VALUES (?,?,?,?,?,?,?,?)",
						new Object[] { entity.getAge(), entity.getBirthday(),
								entity.getEmail(), entity.getGender(),
								entity.getNickname(), entity.getPassword(),
								entity.getQq(), entity.getUsername() });
	}

	/**
	 * @param entity
	 * @see com.ketayao.dao.GenericDao#update(java.lang.Object)
	 */
	@Override
	public void update(User entity) {
		this.getSimpleJdbcTemplate()
				.update("UPDATE lovej_user SET age=?,birthday=?,email=?,gender=?,nickname=?,password=?,qq=?,username=?  WHERE id=?",
						new Object[] { entity.getAge(), entity.getBirthday(),
								entity.getEmail(), entity.getGender(),
								entity.getNickname(), entity.getPassword(),
								entity.getQq(), entity.getUsername(),
								entity.getId() });
	}

	/**
	 * @param id
	 * @see com.ketayao.dao.GenericDao#delete(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) {
		this.getJdbcTemplate().update("DELETE FROM lovej_user WHERE id=" + id);
	}

	/**
	 * @return
	 * @see com.ketayao.dao.GenericDao#deleteAll()
	 */
	@Override
	public Integer deleteAll() {
		return this.getJdbcTemplate().update("DELETE FROM lovej_user ");
	}

	/**
	 * @param id
	 * @return
	 * @see com.ketayao.dao.GenericDao#get(java.lang.Integer)
	 */
	@Override
	public User get(Integer id) {
		List<User> users = this.getSimpleJdbcTemplate().query("SELECT * FROM lovej_user WHERE id=" + id + ")", BeanPropertyRowMapper.newInstance(User.class));
		if (users.size() > 0) {
			return users.get(0);
		}
		return null;
	}

	/**
	 * @return
	 * @see com.ketayao.dao.GenericDao#findAll()
	 */
	@Override
	public List<User> findAll() {
		return this.getSimpleJdbcTemplate().query("SELECT * FROM lovej_user",
				BeanPropertyRowMapper.newInstance(User.class));
	}

	/**
	 * @param username
	 * @param password
	 * @return
	 * @see com.ketayao.dao.UserDao#login(java.lang.String, java.lang.String)
	 */
	@Override
	public User login(String username, String password)
			throws NotFoundUserException, NotMatchUserPasswordException {
		password = SimpleEncrypt.encrypt(password);

		String queryString = "SELECT * FROM lovej_user user WHERE user.username='"
				+ username + "'";
		
		/**
		 * User user = this.getJdbcTemplate().queryForObject(queryString, ParameterizedBeanPropertyRowMapper.newInstance(User.class));
		 * queryForObject EmptyResultDataAccessException
		 * so use query instead of queryForObject
		**/
		
		List<User> users = this.getSimpleJdbcTemplate().query(queryString, BeanPropertyRowMapper.newInstance(User.class));
		if (users.size() < 1) {
			throw new NotFoundUserException("not found user:" + username);
		} else {
			User user = users.get(0);
			if (!user.getPassword().equals(password)) {
				throw new NotMatchUserPasswordException(
						"user's password is error!");
			}
			return user;
		}
	}
}
