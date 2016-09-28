package com.gzhd.web.action;

import java.io.ByteArrayInputStream;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;

import com.gzhd.common.ConstantValues;
import com.gzhd.util.RandomCodeUtil;
import com.opensymphony.xwork2.ActionContext;

/**
 * @Description:登陆页面以此获取验证码
 * @time:2015年7月24日 上午11:07:09
 */
@Action(value="securityCode", results = {
		@Result(name = "success", type="stream", params = {"contentType","image/jpeg","inputName","inputStream"})})  //params配置：
@Scope("prototype")
public class SecurityCodeAction extends BaseAction<Object> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = Logger.getLogger(SecurityCodeAction.class);
	
	private ByteArrayInputStream inputStream;

	public String getCodeForBack() throws Exception {
		
		RandomCodeUtil rdnu = RandomCodeUtil.Instance();
		
		this.setInputStream(rdnu.getImage());// 取得带有随机字符串的图片
		
		ActionContext.getContext().getSession().put(ConstantValues.BACK_SESSION_CODE, rdnu.getString());// 取得随机字符串放入HttpSession
		
		return SUCCESS;
	}
	
	public String getCodeForFront() throws Exception {
		
		RandomCodeUtil rdnu = RandomCodeUtil.Instance();
		
		this.setInputStream(rdnu.getImage());// 取得带有随机字符串的图片
		
		ActionContext.getContext().getSession().put(ConstantValues.FRONT_SESSION_CODE, rdnu.getString());// 取得随机字符串放入HttpSession
		
		return SUCCESS;
	}

	public void setInputStream(ByteArrayInputStream inputStream) {
		this.inputStream = inputStream;
	}

	public ByteArrayInputStream getInputStream() {
		return inputStream;
	}
}
