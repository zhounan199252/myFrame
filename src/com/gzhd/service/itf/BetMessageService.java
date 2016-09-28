package com.gzhd.service.itf;

import java.util.List;

import com.gzhd.model.BetMessageModel;
import com.gzhd.model.PageModel;


public interface BetMessageService {
	
	public static final String BEAN_NAME = "com.gzhd.service.itf.BetMessageService";

	/**
	 * 
	 *保存投注信息
	 */
	public String addBetMessage(BetMessageModel model);
	
	/**
	 * 
	 *分页获取
	 */
	public PageModel getForPageModel(int pageNum, int pageSize,BetMessageModel model);
	
	/**
	 * 
	 *获取
	 */
	public List<BetMessageModel> getAll(BetMessageModel model);
	
	/**
	 * 根据id删除信息
	 * 

	 */
	public void deleteById(String id);
	
	
	/**
	 * 更新投注信息
	 * 
	 
	 */
	public void updateBetMessage(BetMessageModel model);



}
