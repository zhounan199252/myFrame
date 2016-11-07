package interceptor;


import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;



/**
 * @Description:字符编码过滤器，强制性把编码设置成utf-8
 * @author:黄威生
 * @time:2015年7月24日 上午10:06:53
 */
public class EncodingInterceptor extends MethodFilterInterceptor {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1101734000361756730L;
	private static final Logger logger = Logger.getLogger(EncodingInterceptor.class);

	@Override
	protected String doIntercept(ActionInvocation actionInvocation) throws Exception {

		ServletActionContext.getResponse().setCharacterEncoding("utf-8");
		ServletActionContext.getRequest().setCharacterEncoding("utf-8");
		
		return actionInvocation.invoke();
	}

}
