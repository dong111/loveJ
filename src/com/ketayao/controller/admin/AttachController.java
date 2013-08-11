/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, ketayao.com
 * Filename:		com.ketayao.controller.admin.AttachController.java
 * Class:			AttachController
 * Date:			2012-4-16
 * Author:			<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version          1.1.0
 * Description:		
 *
 * </pre>
 **/
 
package com.ketayao.controller.admin;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ketayao.pojo.Attach;
import com.ketayao.service.AttachService;
import com.ketayao.system.SystemConfig;

/** 
 * 	
 * @author 	<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version  1.1.0
 * @since   2012-4-16 下午5:57:58 
 */
@Controller
@RequestMapping("/admin/content/attach")
public class AttachController {
	protected final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	private AttachService attachService;
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(Integer id, Integer articleId, Integer categoryId, Integer pageIndex) {
		String resultView = "redirect:/admin/content/updateArticle?id=" + articleId;
		if (categoryId != null) {
			resultView += "&categoryId=" + categoryId;
		}
		if (pageIndex != null) {
			resultView += "&pageIndex=" + pageIndex;
		}
		
		attachService.delete(id);
		return resultView; 
	}
	


	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String upload(HttpServletRequest request,
			@RequestParam MultipartFile file,
			Attach attach, Integer categoryId, Integer pageIndex) {
		
		String resultView = "redirect:/admin/content/updateArticle?id=" + attach.getArticleId();
		if (categoryId != null) {
			resultView += "&categoryId=" + categoryId;
		}
		if (pageIndex != null) {
			resultView += "&pageIndex=" + pageIndex;
		}

		resultView += "&message=";
		//  返回信息
		String message = "";
		
		// 文件保存目录路径
		String savePath = SystemConfig.ROOT_DIR
				+ SystemConfig.getConfig().get("lovej.upload.file");

		// 文件保存目录URL
		// String saveUrl = SystemConfig.getConfig().get("lovej.upload.file");

		// 定义允许上传的文件扩展名
		Map<String, String> extMap = SystemConfig.EXTEND_TYPE;

		// 允许最大上传文件大小
		long maxSize = Long.parseLong(SystemConfig.getConfig().get(
				"lovej.upload.maxSize"));

		// 检查文件大小

		if (file.getSize() > maxSize) {
			message = "上传文件大小超过限制。";
			return resultView + message;
		}
		// 检查扩展名
		String fileName = file.getOriginalFilename();
		String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1)
				.toLowerCase();
		if (!Arrays.<String> asList(extMap.get("file").split(",")).contains(
				fileExt)) {
			message = "上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get("file")
					+ "格式。";
			return resultView + message;
		}

		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		
		String newFileName = "";
		if (attach.getDescription() != null && !attach.getDescription().trim().equals("")) {
			newFileName = attach.getDescription();
		} else {
			newFileName = fileName.substring(0, fileName.lastIndexOf("."));
		}
		
		newFileName += "_" + df.format(new Date()) + "." + fileExt;
		File uploadedFile = new File(savePath, newFileName);

		try {
			FileUtils.writeByteArrayToFile(uploadedFile, file.getBytes());

			attach.setUrl(newFileName);
			attachService.create(attach);

			message = "文件上传成功";
		} catch (IOException e) {
			logger.error("文件上传失败:" + e.getMessage());
			message = "文件上传失败";
		}

		message = file.getOriginalFilename() + "文件上传成功";
		return resultView;
	}
}
