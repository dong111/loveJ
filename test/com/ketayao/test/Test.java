/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, ketayao.com
 * Filename:		com.ketayao.test.Test.java
 * Class:			Test
 * Date:			2012-4-7
 * Author:			<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version          1.1.0
 * Description:		
 *
 * </pre>
 **/
 
package com.ketayao.test;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.ketayao.util.HtmlUtil;


/** 
 * 	
 * @author 	<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version  1.1.0
 * @created 2012-4-7 上午11:15:46 
 */

public class Test {

	/**  
	 * 描述
	 * @param args  
	 */
	public static void main(String[] args) {
		System.out.println(HtmlUtil.highLight("豁哥哥Spring", "哥哥spring", HtmlUtil.HIGH_LIGHT_SIMPLE_STYLE));
		
		Date date = new Date(0L);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(date.getTime());
		System.out.println(format.format(date));
	}

}
