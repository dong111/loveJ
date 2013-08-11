/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, ketayao.com
 * Filename:		com.ketayao.controller.blog.DownloadController.java
 * Class:			DownloadController
 * Date:			2012-4-16
 * Author:			<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version          1.1.0
 * Description:		
 *
 * </pre>
 **/

package com.ketayao.controller.blog;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ketayao.pojo.Attach;
import com.ketayao.service.AttachService;
import com.ketayao.system.SystemConfig;

/**
 * 
 * @author <a href="mailto:ketayao@gmail.com">ketayao</a> Version 1.1.0
 * @since 2012-4-16 下午6:25:43
 */
@Controller
@RequestMapping("/blog")
public class DownloadController {
	protected final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	private AttachService attachService;

	@RequestMapping(value = "/download/{id}", method = RequestMethod.GET)
	public void downloadFile(HttpServletResponse response,
			@PathVariable Integer id) throws Exception {
		
		Attach attach = attachService.get(id);
		if (attach == null) {
			return;
		}

		response.setCharacterEncoding("utf-8");
		response.setContentType("multipart/form-data");

		response.setHeader("Content-Disposition", "attachment;fileName="
				+ attach.getUrl());

		try {
			File file = new File(attach.getUrl());
			String savePath = SystemConfig.ROOT_DIR
					+ SystemConfig.getConfig().get("lovej.upload.file");
			InputStream inputStream = new FileInputStream(savePath + file);
			OutputStream os = response.getOutputStream();
			byte[] b = new byte[1024];
			int length;
			while ((length = inputStream.read(b)) > 0) {
				os.write(b, 0, length);
			}
			inputStream.close();
			
			attach.setDownload(attach.getDownload() +1);
			attachService.update(attach);
		} catch (FileNotFoundException e) {
			logger.error(e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
	}
}
