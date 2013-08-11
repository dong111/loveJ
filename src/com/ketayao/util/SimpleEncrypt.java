/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, ketayao.com
 * Filename:		com.ketayao.util.EncryptMD5.java
 * Class:			EncryptMD5
 * Date:			2011-11-12
 * Author:			<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version          1.0.0
 * Description:		
 *
 * </pre>
 **/

package com.ketayao.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * @author <a href="mailto:ketayao@gmail.com">ketayao</a> Version 1.0.0
 * @created 2011-11-12 下午5:14:34
 */

public class SimpleEncrypt {
	protected static final Log logger = LogFactory.getLog(SimpleEncrypt.class);
	
	private SimpleEncrypt() {

	}

	/**
	 * The hex digits.
	 */
	private static final String[] hexDigits = { "0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

	/**
	 * Transform the byte array to hex string.
	 * 
	 * @param b
	 * @return
	 */
	private static String byteArrayToHexString(byte[] b) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			resultSb.append(byteToHexString(b[i]));
		}
		return resultSb.toString();
	}

	/**
	 * Transform a byte to hex string.
	 * 
	 * @param b
	 * @return
	 */
	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n = 256 + n;

		// get the first four bit
		int d1 = n / 16;

		// get the second four bit
		int d2 = n % 16;

		return hexDigits[d1] + hexDigits[d2];
	}

	public static String encrypt(String s) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(s.getBytes());			
			return byteArrayToHexString(md.digest());
		} catch (NoSuchAlgorithmException e) {
			logger.error("不支持的加密算法:MD5");
			return null;
		}
	}
}
