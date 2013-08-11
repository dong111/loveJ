/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, ketayao.com
 * Filename:		com.ketayao.service.AttachService.java
 * Class:			AttachService
 * Date:			2012-4-15
 * Author:			<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version          1.1.0
 * Description:		
 *
 * </pre>
 **/

package com.ketayao.service;

import java.util.List;

import com.ketayao.pojo.Attach;

/**
 * 
 * @author <a href="mailto:ketayao@gmail.com">ketayao</a> Version 1.1.0
 * @since 2012-4-15 下午4:06:16
 */

public interface AttachService {

	public void create(Attach attach);

	public void delete(Integer id);
	
	public void update(Attach attach);

	public Attach get(Integer id);

	public List<Attach> find(Integer articleId);
}
