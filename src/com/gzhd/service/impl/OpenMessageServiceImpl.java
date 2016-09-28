package com.gzhd.service.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.gzhd.dao.itf.BaseDao;
import com.gzhd.domain.OpenMessage;
import com.gzhd.model.OpenMessageModel;
import com.gzhd.model.PageModel;
import com.gzhd.service.itf.OpenMessageService;

@Service(OpenMessageService.BEAN_NAME)
public class OpenMessageServiceImpl implements OpenMessageService {

	
	
	
	@Resource(name = BaseDao.BEAN_NAME)
	private BaseDao<OpenMessage> baseDao;
	
	@Override
	public String addOpenMessage(OpenMessageModel model) {
		
		OpenMessage openMessage= new OpenMessage();

		BeanUtils.copyProperties(model, openMessage);
		baseDao.save(openMessage);
		
		return null;
	}

	@Override
	public OpenMessageModel getOpenMessage(OpenMessageModel model) {
	     
		Map<String, Object> params = new HashMap<String, Object>();

		StringBuilder queryHql = new StringBuilder(
				"from OpenMessage a where 1=1 ");

		if (StringUtils.isNotBlank(model.getType())) {
			queryHql.append(" and a.type like :type");
			params.put("type", "%%" + model.getType() + "%%" );
		}
	
		if (StringUtils.isNotBlank(model.getExpect())) {
			queryHql.append(" and a.expect like :expect");
			params.put("expect", "%%" + model.getExpect() + "%%" );
		}
		
		OpenMessage openMessage=baseDao.get(queryHql.toString(), params);
		if(openMessage!=null){
			BeanUtils.copyProperties(openMessage,model);	
		}else{
			model =null;
		}

		return model;
	}

	@Override
	public PageModel getForPageModel(int pageNum, int pageSize,
			OpenMessageModel model) {

		Map<String, Object> params = new HashMap<String, Object>();

		StringBuilder queryHql = new StringBuilder(
				"from OpenMessage a where 1=1 ");
		
		if (StringUtils.isNotBlank(model.getType())) {
			queryHql.append(" and a.type like :type");
			params.put("type", "%%" + model.getType() + "%%" );
		}
		
		if (StringUtils.isNotBlank(model.getOpentime())) {
			queryHql.append(" and a.opentime <= :opentime");
			params.put("opentime",model.getOpentime());
		}
        
		if (StringUtils.isNotBlank(model.getExpect())) {
			queryHql.append(" and a.expect like :expect");
			params.put("expect", "%%" + model.getExpect() + "%%" );
		}
		queryHql.append(" order by a.opentime desc");

		List<OpenMessage> list = baseDao.find(queryHql.toString(), params,
				pageNum, pageSize);

		List<OpenMessageModel> modelList = new ArrayList<OpenMessageModel>();

		for (OpenMessage openMessage : list) {
			OpenMessageModel openMessageModel = new OpenMessageModel();
			BeanUtils.copyProperties(openMessage, openMessageModel);
			modelList.add(openMessageModel);
		}

		StringBuffer countHql = new StringBuffer("select count(1) ")
				.append(queryHql);
		int allRows = baseDao.count(countHql.toString(), params).intValue();

		return new PageModel(pageNum, pageSize, modelList, allRows);
	}

	@Override
	public void deleteById(String id) {
		String[] ids = id.split(",");
		for (String sigleId : ids) {
			OpenMessage openMessage = baseDao.get(OpenMessage.class, sigleId);
			baseDao.delete(openMessage);
		}
		
	}



}
