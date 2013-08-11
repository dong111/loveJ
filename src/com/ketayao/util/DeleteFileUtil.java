package com.ketayao.util;

import java.io.File;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 	
 * @author 	<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version  1.1.0
 * @since   2012-4-15 下午2:09:12
 */
public class DeleteFileUtil {
	protected static final Log logger = LogFactory.getLog(DeleteFileUtil.class);
	
	/**
	 * 删除文件，可以是单个文件或文件夹
	 * 
	 * @param fileName
	 *            待删除的文件名
	 * @return 文件删除成功返回true,否则返回false
	 */
	public static boolean delete(String fileName) {
		File file = new File(fileName);
		if (!file.exists()) {
			if (logger.isDebugEnabled()) {
				logger.debug("删除文件失败：" + fileName + "文件不存在");
			}
			return false;
		} else {
			if (file.isFile()) {
				return deleteFile(fileName);
			} else {
				return deleteDirectory(fileName);
			}
		}
	}

	/**
	 * 删除单个文件
	 * 
	 * @param fileName
	 *            被删除文件的文件名
	 * @return 单个文件删除成功返回true,否则返回false
	 */
	public static boolean deleteFile(String fileName) {
		File file = new File(fileName);
		if (file.isFile() && file.exists()) {
			file.delete();
			if (logger.isDebugEnabled()) {
				logger.debug("删除单个文件:" + fileName + "成功！");
			}
			return true;
		} else {
			if (logger.isDebugEnabled()) {
				logger.debug("删除单个文件:" + fileName + "失败！");
			}
			return false;
		}
	}

	/**
	 * 删除目录（文件夹）以及目录下的文件
	 * 
	 * @param dir
	 *            被删除目录的文件路径
	 * @return 目录删除成功返回true,否则返回false
	 */
	public static boolean deleteDirectory(String dir) {
		// 如果dir不以文件分隔符结尾，自动添加文件分隔符
		if (!dir.endsWith(File.separator)) {
			dir = dir + File.separator;
		}
		File dirFile = new File(dir);
		// 如果dir对应的文件不存在，或者不是一个目录，则退出
		if (!dirFile.exists() || !dirFile.isDirectory()) {
			if (logger.isDebugEnabled()) {
				logger.debug("删除目录失败:" + dir + "目录不存在！");
			}
			return false;
		}
		boolean flag = true;
		// 删除文件夹下的所有文件(包括子目录)
		File[] files = dirFile.listFiles();
		for (int i = 0; i < files.length; i++) {
			// 删除子文件
			if (files[i].isFile()) {
				flag = deleteFile(files[i].getAbsolutePath());
				if (!flag) {
					if (logger.isDebugEnabled()) {
						logger.debug("删除" + dir + "下的" + files[i].getAbsolutePath() + "子文件失败");
					}
					return false;
				}
			}
			// 删除子目录
			else {
				flag = deleteDirectory(files[i].getAbsolutePath());
				if (!flag) {
					if (logger.isDebugEnabled()) {
						logger.debug("删除" + dir + "下的" + files[i].getAbsolutePath() + "子目录失败");
					}
					return false;
				}
			}
		}

		// 删除当前目录
		if (dirFile.delete()) {
			if (logger.isDebugEnabled()) {
				logger.debug("删除目录" + dir + "成功！");
			}
			return true;
		} else {
			if (logger.isDebugEnabled()) {
				logger.debug("删除目录" + dir + "失败！");
			}
			return false;
		}
	}

}
