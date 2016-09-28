package com.gzhd.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.log4j.Logger;


/**
 * @Description:获取异常信息
 * @time:2015年8月7日 上午11:50:09
 */
public class ExceptionUtil {

	private static final Logger logger = Logger.getLogger(ExceptionUtil.class);

	
	/**
	 * @Description:
	 * @param ex
	 * @return
	 * @return:String 异常信息
	 * @exception:
	 * @time:2015年8月7日 上午11:50:47
	 */
	public static String getExceptionMessage(Exception ex) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		ex.printStackTrace(pw);
		String errorMessage = sw.toString();
		pw.close();
		try {
			sw.close();
		} catch (IOException e) {
			logger.error(e);
		}
		return errorMessage;
	}

}
