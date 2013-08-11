/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, ketayao.com
 * Filename:		com.ketayao.util.HtmlParser.java
 * Class:			HtmlParser
 * Date:			2011-11-24
 * Author:			<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version          1.0.0
 * </pre>
 **/

package com.ketayao.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author <a href="mailto:ketayao@gmail.com">ketayao</a> Version 1.0.0
 * 2011-11-24 下午12:47:29
 */

public class HtmlUtil {
	// 高亮样式
	public final static String HIGH_LIGHT_SIMPLE_STYLE = "<font style='color:red;'></font>";	
	private HtmlUtil() {

	}
	/**
	 * 删除input字符串中的html格式
	 * @param input
	 * @param length
	 * @return
	 */
	public static String filterHtml(String input, int length) {
		if (input == null || input.trim().equals("")) {
			return "";
		}
		// 去掉所有html元素,
		String str = input.replaceAll("\\&[a-zA-Z]{1,10};", "")
				.replaceAll("<[^>]*>", "");
		str = str.replaceAll("[(/>)<]", "");
		int len = str.length();
		if (len <= length) {
			return str;
		} else {
			str = str.substring(0, length);
			str += "......";
		}
		return str;
	}
	/**
	 * 关键字高亮
	 * @param input
	 * @param keyword
	 * @param style
	 * @return
	 */
	public static String highLight(String input, String keyword, String style) {
		int mark = style.indexOf("</");
		String before = style.substring(0, mark);
		String after = style.substring(mark);
		
		//String result = before + keyword + after;
		// 忽略大小写替换
		//String out = input.replaceAll("(?i)" + keyword, result);
		
		Pattern p = Pattern.compile(keyword, Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(input);
		StringBuffer buf = new StringBuffer();
		while(m.find()) {
			m.appendReplacement(buf, before + m.group() + after);
		}
		m.appendTail(buf);

		return buf.toString();
	}
}
