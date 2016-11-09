package interceptor;


import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import commom.ConstantValues;

import domain.UserModel;


/**
 * @Description:session拦截器，用于判断当前用户是否已经登陆
 */
public class SessionInterceptor extends MethodFilterInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(SessionInterceptor.class);

	@Override
	protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
		UserModel userModel = (UserModel) ServletActionContext.getRequest().getSession().getAttribute(ConstantValues.CURRENT_USER_LOGIN);
		//logger.info("进入session拦截器->访问路径为[" + ServletActionContext.getRequest().getServletPath() + "]");
		if(null == userModel) {
			String errMsg = "您还没有登录或登录已超时，请重新登录后再使用本功能！";
			ActionContext.getContext().put("msg", errMsg);
			return "noSession";
		}
		
		return actionInvocation.invoke();
	}

}
