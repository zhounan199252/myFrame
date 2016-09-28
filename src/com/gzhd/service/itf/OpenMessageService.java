package com.gzhd.service.itf;

import com.gzhd.model.OpenMessageModel;
import com.gzhd.model.PageModel;



public interface OpenMessageService {
	
	public static final String BEAN_NAME = "com.gzhd.service.itf.OpenMessageService";
	
	/**
	 * 
	 *分页获取
	 */
	public PageModel getForPageModel(int pageNum, int pageSize,OpenMessageModel model);

	/**
	 * 
	 *保存开奖信息
	 */
	public String addOpenMessage(OpenMessageModel model);
	
	
	/**
	 * 
	 *查询信息
	 */
	public OpenMessageModel getOpenMessage(OpenMessageModel model);
	
	/**
	 * 根据id删除信息
	 *

	 */
	public void deleteById(String id);
}
