package com.gzhd.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.gzhd.common.ConstantValues;
import com.gzhd.dao.itf.BaseDao;
import com.gzhd.domain.BetMessage;
import com.gzhd.model.BetMessageModel;
import com.gzhd.model.FrontUserModel;
import com.gzhd.model.PageModel;
import com.gzhd.service.itf.BetMessageService;
import com.gzhd.service.itf.FrontUserService;
import com.sun.corba.se.impl.orbutil.closure.Constant;
import com.sun.org.apache.bcel.internal.classfile.ConstantValue;

@Service(BetMessageService.BEAN_NAME)
public class BetMessageServiceImpl implements BetMessageService {

	//private static final Logger logger = Logger.getLogger(BackUserServiceImpl.class);

	@Resource(name = BaseDao.BEAN_NAME)
	private BaseDao<BetMessage> baseDao;
	@Resource(name = FrontUserService.BEAN_NAME)
	private FrontUserService frontUserService;

	@Override
	public String addBetMessage(BetMessageModel model) {
		BetMessage betMessage = new BetMessage();

		BeanUtils.copyProperties(model, betMessage);
		Serializable s = baseDao.save(betMessage);

		return (String) s;
	}

	@Override
	public PageModel getForPageModel(int pageNum, int pageSize,
			BetMessageModel model) {

		Map<String, Object> params = new HashMap<String, Object>();

		StringBuilder queryHql = new StringBuilder(
				"from BetMessage a where 1=1 ");

		if (StringUtils.isNotBlank(model.getBetType())) {
			queryHql.append(" and a.betType like :betType");
			params.put("betType", "%%" + model.getBetType() + "%%" );
		}
		if (StringUtils.isNotBlank(model.getBetChildType())) {
			queryHql.append(" and a.betChildType = :betChildType");
			params.put("betChildType",model.getBetChildType()  );
		}
		if (StringUtils.isNotBlank(model.getExchangeFlag())) {
			queryHql.append(" and a.exchangeFlag = :exchangeFlag");
			params.put("exchangeFlag", model.getExchangeFlag());
		}

		if (StringUtils.isNotBlank(model.getBetPersonName())) {
			String userId= frontUserService.getIdByFrontUsername(model.getBetPersonName());
			if (StringUtils.isNotBlank(userId)) {
				model.setBetPerson(userId);
			}else{
				model.setBetPerson("no");
			}
		
		}

		if (StringUtils.isNotBlank(model.getBetPerson())) {
			queryHql.append(" and a.betPerson = :betPerson");
			params.put("betPerson", model.getBetPerson());
		}
		if (StringUtils.isNotBlank(model.getBetPeriod())) {
			queryHql.append(" and a.betPeriod like :betPeriod");
			params.put("betPeriod","%%" +model.getBetPeriod() + "%%" );
		}

		queryHql.append(" order by a.betDate desc");

		List<BetMessage> list = baseDao.find(queryHql.toString(), params,
				pageNum, pageSize);

		List<BetMessageModel> modelList = new ArrayList<BetMessageModel>();

		for (BetMessage betMessage : list) {
		
			BetMessageModel betMessageModel = new BetMessageModel();
			BeanUtils.copyProperties(betMessage, betMessageModel);
			 String  betChildType=betMessageModel.getBetChildType();	 
			 if(StringUtils.isNotBlank(betChildType)){
				 betMessageModel.setBetChildType(ConstantValues.getAwardType().get(betChildType)); 
			 }
			FrontUserModel frontUserModel = frontUserService
					.getUserById(betMessageModel.getBetPerson());
			if (frontUserModel.getUsername() != null) {
				betMessageModel.setBetPersonName(frontUserModel.getUsername());
			} else {
				betMessageModel.setBetPersonName("");
			}

			modelList.add(betMessageModel);
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
			BetMessage betMessage = baseDao.get(BetMessage.class, sigleId);
			baseDao.delete(betMessage);
		}

	}

	@Override
	public void updateBetMessage(BetMessageModel model) {
		String[] ids = model.getId().split(",");
		if(model.getExchangeFlag().equals("1")){
			model.setExchangeFlag("已兑换");
		}else if(model.getExchangeFlag().equals("2")){
			model.setExchangeFlag("未中奖");
		}else{
			model.setExchangeFlag("金额过大");
		}
		for (String sigleId : ids) {
			model.setId(sigleId);
			BetMessage betMessage = baseDao
					.get(BetMessage.class, model.getId());
			betMessage.setExchangeFlag(model.getExchangeFlag());
			baseDao.save(betMessage);
		}

	}

	@Override
	public List<BetMessageModel> getAll(BetMessageModel model) {
		Map<String, Object> params = new HashMap<String, Object>();

		StringBuilder queryHql = new StringBuilder(
				"from BetMessage a where 1=1 ");

		if (StringUtils.isNotBlank(model.getBetType())) {
			queryHql.append(" and a.betType like :betType");
			params.put("betType", "%%" + model.getBetType() + "%%" );
		}
	
		if (StringUtils.isNotBlank(model.getExchangeFlag())) {
			queryHql.append(" and a.exchangeFlag = :exchangeFlag");
			params.put("exchangeFlag", model.getExchangeFlag() );
		}

		if (StringUtils.isNotBlank(model.getBetPeriod())) {
			queryHql.append(" and a.betPeriod like :betPeriod");
			params.put("betPeriod","%%" +model.getBetPeriod() + "%%" );
		}

		queryHql.append(" order by a.betDate desc");

		List<BetMessage> list = baseDao.find(queryHql.toString(), params);
		List<BetMessageModel> modelList = new ArrayList<BetMessageModel>();

		for (BetMessage betMessage : list) {
			BetMessageModel betMessageModel = new BetMessageModel();
			BeanUtils.copyProperties(betMessage, betMessageModel);

			FrontUserModel frontUserModel = frontUserService
					.getUserById(betMessageModel.getBetPerson());
			if (frontUserModel.getUsername() != null) {
				betMessageModel.setBetPersonName(frontUserModel.getUsername());
			} else {
				betMessageModel.setBetPersonName("");
			}

			modelList.add(betMessageModel);
		}

	

		return  modelList;
	}

}
