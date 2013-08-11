/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, ketayao.com
 * Filename:		com.ketayao.dao.imp.AbstractPageDaoImp.java
 * Class:			AbstractPageDaoImp
 * Date:			2012-4-12
 * Author:			<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version          1.1.0
 * Description:		
 *
 * </pre>
 **/

package com.ketayao.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

import com.ketayao.dao.PageInfo;

/**
 * 
 * @author <a href="mailto:ketayao@gmail.com">ketayao</a> Version 1.1.0
 * @created 2012-4-12 下午12:31:37
 */

public abstract class AbstractDaoImp<T> extends SimpleJdbcDaoSupport {

	protected final Log logger = LogFactory.getLog(getClass());

	/**
	 * 
	 * 使用了游标，减少数据的访问
	 * 
	 * @param pageInfo
	 * @param sql
	 * @param rowMapper
	 * @return
	 */
	public List<T> findPage(PageInfo pageInfo, String sql,
			RowMapper<T> rowMapper) {
		List<T> result = new ArrayList<T>();

		Connection connection = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;

		try {

			connection = this.getJdbcTemplate().getDataSource().getConnection();

			pstat = connection.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = pstat.executeQuery();

			// 使用游标来得到总条数
			rs.last();
			int totalCount = rs.getRow();

			pageInfo.setTotalRec(totalCount);

			int startIndex = (pageInfo.getPageIndex() - 1)
					* pageInfo.getPageSize() + 1;

			if (totalCount > 0) {
				rs.absolute(startIndex);

				int index = 0;
				do {
					result.add(rowMapper.mapRow(rs, index++));
				} while (rs.next() && index < pageInfo.getPageSize());
			}

		} catch (Exception e) {
			logger.error("find page is ERROR, from sql:[" + sql + "]\n"
					+ e.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstat != null) {
					pstat.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				logger.error("close Connection or PreparedStatement, ResultSet Error."
						+ e.getMessage());
			}
		}

		return result;
	}
}
