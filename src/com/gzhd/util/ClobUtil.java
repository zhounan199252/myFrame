package com.gzhd.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Clob;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialClob;
import javax.sql.rowset.serial.SerialException;

import org.apache.commons.lang3.StringUtils;


/**
 * @Description:大文本（Clob）存取工具类
 * @time:2015年7月28日 上午10:21:19
 */
public class ClobUtil {

	
	/**
	 * @Description:把clob转成成String
	 * @author: 黄威生
	 * @param c
	 * @return
	 * @return:String
	 * @exception:
	 * @time:2015年7月28日 上午10:22:07
	 */
	public static String clobToString(Clob c) {
		StringBuffer s = new StringBuffer();
		if (c != null) {
			try {
				BufferedReader bufferRead = new BufferedReader(c.getCharacterStream());
				try {
					String str;
					while (null != (str = bufferRead.readLine())) {
						s.append(str).append("\n");
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return s.toString();
	}

	
	/**
	 * @Description:String转成成clob
	 * @author: 黄威生
	 * @param s
	 * @return
	 * @return:Clob
	 * @exception:
	 * @time:2015年7月28日 上午10:22:44
	 */
	public static Clob stringToClob(String s) {
		Clob c = null;
		try {
			if (!StringUtils.isBlank(s)) {
				c = new SerialClob(s.toCharArray());
			}
		} catch (SerialException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}

}
