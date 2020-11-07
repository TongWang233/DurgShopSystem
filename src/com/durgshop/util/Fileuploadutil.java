package com.durgshop.util;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
//import java.util.logging.Logger;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;

//import org.slf4j.Logger;

//import org.apache.*;
import org.springframework.web.multipart.MultipartFile;

public class Fileuploadutil {

	private static final String IMAGES = "uploads";

	private static Logger logger = Logger.getLogger(Fileuploadutil.class);
	private static String getUploadPath(ServletContext context) {
		String base = context.getRealPath(".");
		if (base != null) {
			File path = new File(base, IMAGES);
			path.mkdirs();
			return path.getAbsolutePath();
		}
		return null;
	}

	/****
	 * @param file
	 * @return 成功返回文件信息，失败返回-null
	 */

	public static final FileResult uploadFile(ServletContext context, MultipartFile file) {
		String path = getUploadPath(context);
		FileResult result = new FileResult(false, null);

		if (path != null && file != null && !file.isEmpty()) {
			long realSize = file.getSize();
			result.setFileSize(realSize);
			String fileName = file.getOriginalFilename();
			result.setFileName(fileName);
			String extName = fileName.substring(fileName.lastIndexOf(".") + 1);
			result.setExtName(extName);
			String uuid = UUID.randomUUID().toString();
			String newFileName = uuid + "." + extName;
			result.setServerPath(IMAGES + "/" + newFileName);
			File dest = new File(path, newFileName);
			logger.info("上传路径： " + dest.getAbsolutePath());
			try {
				file.transferTo(dest);
				result.setSuccess(true);
			} catch (IllegalStateException | IOException e) {
				logger.error("文件上传失败!", e);
				result.setMsg("文件上传失败！" + e.getMessage());
			}
		}
		return result;
	}

}
