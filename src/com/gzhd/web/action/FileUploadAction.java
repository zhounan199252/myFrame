
package com.gzhd.web.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.context.annotation.Scope;

import com.gzhd.model.FileUploadModel;
import com.gzhd.util.TimeUtil;

import net.fckeditor.response.UploadResponse;


@Action(value = "fileUpload", results = { //
		
})
@Scope("prototype")
public class FileUploadAction extends BaseAction<FileUploadModel> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger.getLogger(FileUploadAction.class);
	
	
	/**
	 * fck上传的图片
	 */
	public void uploadFCKImage() {
		
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		
		try {
			String imageExtension = FilenameUtils.getExtension(model.getNewFileFileName());
			
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			
			String dateFile = TimeUtil.getCurDate("/yyyy/MM/dd/");
			
			String path = request.getServletContext().getRealPath("/file_uploads/" + dateFile);
			
			File dirFile = new File(path);
			
			if(!dirFile.exists()) {
				dirFile.mkdirs();
			}
			
			bis = new BufferedInputStream(new FileInputStream(model.getNewFile()));
			
			String newFileName = UUID.randomUUID().toString() + "." + imageExtension;
			
			File destFile = new File(path, newFileName);
			
			bos = new BufferedOutputStream(new FileOutputStream(destFile));
			
			byte[] buffer = new byte[1024];
			int length = 0;
			while (-1 != (length = bis.read(buffer))) {
				bos.write(buffer, 0, length);
			}
			
			//返回url给fck
			UploadResponse ok = UploadResponse.getOK("/lottery/file_uploads" + dateFile + newFileName);
			
			response.getWriter().print(ok);
			
			response.flushBuffer();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(null != bis) {
					bis.close();
				}
			} catch (IOException e2) {
				e2.printStackTrace();
			}
			try {
				if(null != bos) {
					bos.close();
				}
			} catch (IOException e3) {
				e3.printStackTrace();
			}
		}
	}
}


















