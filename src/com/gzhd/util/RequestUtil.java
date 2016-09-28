package com.gzhd.util;



import javax.servlet.http.HttpServletRequest;


/**
 * @Description:request工具类
 * @time:2015年7月24日 上午10:05:39
 */
public class RequestUtil {

	/**
	 * 获得请求路径
	 * 
	 * @param request
	 * @return
	 */
	public static String getRequestPath(HttpServletRequest request) {
		String requestPath = request.getRequestURI();
		requestPath = requestPath.substring(request.getContextPath().length());// 去掉项目路径
		return requestPath;
	}

}
