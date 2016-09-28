package com.gzhd.web.interceptor;


import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.gzhd.common.ConstantValues;
import com.gzhd.model.BackUserModel;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;


/**
 * @Description:session拦截器，用于判断当前用户是否已经登陆
 * @time:2015年7月24日 上午10:04:49
 */
public class BackSessionInterceptor extends MethodFilterInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(BackSessionInterceptor.class);

	@Override
	protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
		BackUserModel bckUserModel = (BackUserModel) ServletActionContext.getRequest().getSession().getAttribute(ConstantValues.BACK_CURRENT_USER_LOGIN);
		logger.info("进入session拦截器->访问路径为[" + ServletActionContext.getRequest().getServletPath() + "]");
		if(null == bckUserModel) {
			String errMsg = "您还没有登录或登录已超时，请重新登录后再使用本功能！";
			ActionContext.getContext().put("msg", errMsg);
			return "backNoSession";
		}
		
		return actionInvocation.invoke();
	}

}
