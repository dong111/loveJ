/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, ketayao.com
 * Filename:		com.ketayao.actions.blog.ArticleCommentService.java
 * Class:			ArticleCommentService
 * Date:			2011-12-3
 * Author:			<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version          1.0.0
 * Description:		
 *
 * </pre>
 **/
 
package com.ketayao.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ketayao.dao.PageInfo;
import com.ketayao.pojo.Comment;
import com.ketayao.service.CommentService;

/** 
 * dwr翻页功能
 * @author 	<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version  1.0.0
 * @created 2011-12-3 下午6:31:39 
 */
@Service
public class ArticleCommentService {
	@Autowired
	private CommentService commentService;
	
	private PageInfo commentPage;

	private List<Comment> comments;
	
	public List<Comment> findPageComments(Integer articleId, Integer pageIndex) {
		commentPage = new PageInfo();
		//commentPage.setPageSize(1);
		commentPage.setPageIndex(pageIndex);
		comments = commentService.findPage(articleId, commentPage, Comment.Status.APPROVED);
		return comments;
	}

	public PageInfo getCommentPage() {
		return commentPage;
	}

	public void setCommentPage(PageInfo commentPage) {
		this.commentPage = commentPage;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
}
