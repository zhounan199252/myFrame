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
import com.gzhd.domain.DepositApply;
import com.gzhd.model.DepositApplyModel;
import com.gzhd.model.FrontUserModel;
import com.gzhd.model.PageModel;
import com.gzhd.service.itf.DepositApplyService;
import com.gzhd.service.itf.FrontUserService;


@Service(DepositApplyService.BEAN_NAME)
public class DepositApplyServiceImpl implements DepositApplyService {

	
	
	
	@Resource(name = BaseDao.BEAN_NAME)
	private BaseDao<DepositApply> baseDao;
	@Resource(name = FrontUserService.BEAN_NAME)
	private FrontUserService frontUserService;
		

	@Override
	public String addDepositApply(DepositApplyModel model) {
		
	
	    DepositApply depositApply = new DepositApply();
		BeanUtils.copyProperties(model, depositApply);
		baseDao.save(depositApply);
		return null;
	}

	@Override
	public String updateDepositApply(DepositApplyModel model) {
			DepositApply depositApply = baseDao.get(DepositApply.class, model.getId());
			BeanUtils.copyProperties(model, depositApply, new String[]{"userId", "money", "bankNum", "applyTime"});
			baseDao.save(depositApply);	
		return null;
	}

	@Override
	public PageModel getForPageModel(int pageNum, int pageSize,
			DepositApplyModel model) {
		Map<String, Object> params = new HashMap<String, Object>();

		StringBuilder queryHql = new StringBuilder(
				"from DepositApply a where 1=1 ");

	
		if (StringUtils.isNotBlank(model.getUserName())) {
			String userId= frontUserService.getIdByFrontUsername(model.getUserName());
			if (StringUtils.isNotBlank(userId)) {
				model.setUserId(userId);
			}else{
				model.setUserId("no");
			}
		
		}

		if (StringUtils.isNotBlank(model.getUserId())) {
			queryHql.append(" and a.userId = :userId");
			params.put("userId", model.getUserId());
		}
	
		queryHql.append(" order by a.applyTime desc");

		List<DepositApply> list = baseDao.find(queryHql.toString(), params,
				pageNum, pageSize);

		List<DepositApplyModel> modelList = new ArrayList<DepositApplyModel>();

		for (DepositApply depositApply : list) {
			DepositApplyModel depositApplyModel = new DepositApplyModel();
			BeanUtils.copyProperties(depositApply,depositApplyModel);

			FrontUserModel frontUserModel = frontUserService
					.getUserById(depositApplyModel.getUserId());
			if (frontUserModel.getUsername() != null) {
				depositApplyModel.setUserName(frontUserModel.getUsername());
			} else {
				depositApplyModel.setUserName("");
			}

			modelList.add(depositApplyModel);
		}

		StringBuffer countHql = new StringBuffer("select count(1) ")
				.append(queryHql);
		int allRows = baseDao.count(countHql.toString(), params).intValue();

		return new PageModel(pageNum, pageSize, modelList, allRows);
	}

	@Override
	public DepositApplyModel get(String id) {
		DepositApply depositApply=baseDao.get(DepositApply.class, id);
		
		 DepositApplyModel depositApplyModel = new DepositApplyModel();
		 if(depositApply!=null){
			 BeanUtils.copyProperties(depositApply, depositApplyModel); 
		 }
			
		return depositApplyModel;
	}

	@Override
	public void deleteById(String id) {
		String[] ids = id.split(",");
		for (String sigleId : ids) {
			DepositApply depositApply = baseDao.get(DepositApply.class, sigleId);
			baseDao.delete(depositApply);
		}
		
	}



}
