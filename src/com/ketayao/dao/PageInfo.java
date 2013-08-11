/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, ketayao.com
 * Filename:		com.ketayao.dao.PageInfo.java
 * Class:			PageInfo
 * Date:			2012-4-5
 * Author:			<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version          1.1.0
 * Description:		
 *
 * </pre>
 **/
 
package com.ketayao.dao;

/** 
 * 	
 * @author 	<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version  1.1.0
 * @created 2012-4-5 下午1:42:49 
 */

public class PageInfo {
	/**
	 * 总页数
	 */
	private int totalPage = 1;

	/**
	 * 前一页
	 */
	private int prePage = 1;

	/**
	 * 下一页
	 */
	private int nextPage = 1;

	/**
	 * 总记录数
	 */
	private int totalRec = 0;

	/**
	 * 默认每页记录数
	 */
	private static final int DEFAULT_PAGE_SIZE = 10;

	/**
	 * 每页记录数
	 */
	private int pageSize = DEFAULT_PAGE_SIZE;

	/**
	 * 当前页码
	 */
	private int pageIndex = 1;

	/**
	 * 链接地址
	 */
	private String url = "";

	/**
	 * 分页样式
	 */
	private String pageHtml;

	/**
	 * 分页数组大小
	 */
	private int pageArraySize = 10;
	
	public PageInfo() {

	}
	
	public PageInfo(int totalRec) {
		setTotalRec(totalRec);
	}

	/**
	 * 
	 * 得到当前页码
	 * @return
	 */
	public int getPageIndex() {
		if (pageIndex > totalPage) {
			pageIndex = totalPage;
		}
		
		return pageIndex;
	}

	/**
	 * 
	 * 设置当前页码
	 * @param pageIndex
	 */
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex > 0 ? pageIndex : 1;
	}

	/**
	 * 
	 * 得到下一页码
	 * @return
	 */
	public int getNextPage() {
		nextPage = pageIndex + 1;
		if (nextPage > totalPage) {
			nextPage = totalPage;
		}
		
		return nextPage;
	}

	/**
	 * 
	 * 得到pageSize
	 * @return
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * 
	 * 设置pageSize
	 * @param pageSize
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize > 0 ? pageSize : 10;
	}

	/**
	 * 
	 * 得到前一页码
	 * @return
	 */
	public int getPrePage() {
		prePage = pageIndex - 1;
		if (prePage < 1) {
			prePage = 1;
		}
		return prePage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public int getTotalRec() {
		return totalRec;
	}

	public void setTotalRec(int totalRec) {
		this.totalRec = totalRec;
		totalPage = (totalRec - 1) / pageSize + 1;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setPageHtml(String pageHtml) {
		this.pageHtml = pageHtml;
	}

	public String getPageHtml() {
		if (pageHtml != null && !pageHtml.equals("")) {
			return pageHtml;
		}

		return getPageArraySize();
	}

	public int getPageHtmlHeadSize() {
		return pageArraySize;
	}

	public void setPageHtmlHeadSize(int pageArraySize) {
		this.pageArraySize = pageArraySize;
	}

	/**
	 * 
	 * 分页结构，可搭配CSS，呈现好看的分页
	 * @return
	 */
	private String getPageArraySize() {
		StringBuffer html = new StringBuffer();
		if (getPageIndex() != 1) {
			html.append("<a href=\"" + url + getPrePage() + "\">" + " &lt; "
					+ "</a>");
		}

		if (getPageIndex() >= getPageHtmlHeadSize()) {
			//if (getPageIndex() > getPageHtmlHeadSize()) {
			html.append("<a href=\"" + url + 1 + "\">" + "&lt;&lt;" + "</a>");
			//}

			for (int i = 3; i > 0; i--) {
				html.append("<a href=\"" + url + (getPageIndex() - i) + "\">"
						+ (getPageIndex() - i) + "</a>");
			}

			html.append("<span class=\"current\">" + getPageIndex() + "</span>");

			for (int i = 1; i <= 6 && (getPageIndex() + i) < getTotalPage(); i++) {
				html.append("<a href=\"" + url + (getPageIndex() + i) + "\">"
						+ (getPageIndex() + i) + "</a>");
			}
			
			if (getPageIndex() + 6 < getTotalPage()) {
				html.append("<a href=\"" + url + getTotalPage() + "\">" + "&gt;&gt;"
						+ "</a>");
			}
			
		} else {
			for (int i = 1; i <= getTotalPage() && i <= 10; i++) {
				if (i == getPageIndex()) {
					html.append("<span class=\"current\">" + getPageIndex()
							+ "</span>");
				} else {
					html.append("<a href=\"" + url + i + "\">" + i + "</a>");
				}
			}
			
			if (getTotalPage() > getPageHtmlHeadSize()) {
				html.append("<a href=\"" + url + getTotalPage() + "\">" + "&gt;&gt;"
						+ "</a>");	
			}
		}
		
		if (getPageIndex() != getTotalPage()) {
			html.append("<a href=\"" + url + getNextPage() + "\">" + " &gt; "
					+ "</a>");
		}

		return html.toString();
	}
}
