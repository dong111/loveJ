/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, ketayao.com
 * Filename:		com.ketayao.controller.admin.CommentController.java
 * Class:			CommentController
 * Date:			2012-4-10
 * Author:			<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version          1.1.0
 * Description:		
 *
 * </pre>
 **/
 
package com.ketayao.controller.admin;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ketayao.dao.PageInfo;
import com.ketayao.pojo.Comment;
import com.ketayao.service.CommentService;

/** 
 * 	
 * @author 	<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version  1.1.0
 * @created 2012-4-10 上午10:30:04 
 */
@Controller
@RequestMapping("/admin/content")
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	private static final String READ = "admin/content/comment-read";

	@RequestMapping(value="/readComment", method=RequestMethod.GET)
	public String read(Map<String, Object> map, PageInfo pageInfo) {
		List<Comment> comments = commentService.findPage(pageInfo);
		pageInfo.setUrl("readComment?pageIndex=");
		
		map.put("comments", comments);
		map.put("pageInfo", pageInfo);
		return READ;
	}
	
	@RequestMapping(value="/updateComment", method=RequestMethod.GET)
	public String preUpdate(Map<String, Object> map, Integer id, PageInfo pageInfo) {
		Comment comment = commentService.get(id);
		if (comment.getStatus().equals(Comment.Status.APPROVED)) {
			comment.setStatus(Comment.Status.WAIT_FOR_APPROVE);
		} else {
			comment.setStatus(Comment.Status.APPROVED);
		}
		commentService.update(comment);
		
		return read(map, pageInfo);
	}

	@RequestMapping(value="/deleteComment", method=RequestMethod.GET)
	public String delete(Map<String, Object> map, Integer id, PageInfo pageInfo) {
		commentService.delete(id);
		return read(map, pageInfo);
	}
}
