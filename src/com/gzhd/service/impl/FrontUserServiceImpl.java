package com.gzhd.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.gzhd.dao.itf.BaseDao;
import com.gzhd.domain.FrontUser;
import com.gzhd.model.FrontUserModel;
import com.gzhd.model.PageModel;
import com.gzhd.service.itf.FrontUserService;
import com.gzhd.util.Encrypt;


/**
 * @author hws
 *
 */
@Service(FrontUserService.BEAN_NAME)
public class FrontUserServiceImpl implements FrontUserService {

	private static final Logger logger = Logger.getLogger(FrontUserServiceImpl.class);

	@Resource(name = BaseDao.BEAN_NAME)
	private BaseDao<FrontUser> baseDao;

	@Override
	public FrontUserModel getUserById(String id) {
		FrontUser u = baseDao.get(FrontUser.class, id);
		FrontUserModel model = new FrontUserModel();
		if(u!=null){
	     BeanUtils.copyProperties(u, model);
		}
		return model;
	}

	@Override
	public String addUser(FrontUserModel model) {
		FrontUser user = new FrontUser();

		BeanUtils.copyProperties(model, user, new String[] { "password" });
		user.setPassword(Encrypt.md5AndSha(model.getPassword())); // 密码采用md5加sha双重加密

		Serializable s = baseDao.save(user);
		
		return (String) s;
	}

	@Override
	public FrontUserModel login(FrontUserModel model) {
		Map<String, Object> params = new HashMap<String, Object>();

		params.put("username", model.getUsername());
		params.put("password", Encrypt.md5AndSha(model.getPassword()));

		FrontUser user = baseDao.get("from FrontUser u where u.username = :username and u.password = :password", params);

		if (null == user) {
			return null;
		}

		FrontUserModel u = new FrontUserModel();
		BeanUtils.copyProperties(user, u);

		return u;
	}

	@Override
	public PageModel getForPageModel(int pageNum, int pageSize, FrontUserModel model) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer queryHql = new StringBuffer("from FrontUser u where 1=1 ");
		
		
		if(StringUtils.isNotBlank(model.getRecommender())) {
			queryHql.append(" and u.recommender like :recommender");
			params.put("recommender", model.getRecommender());
		}
		if(StringUtils.isNotBlank(model.getNickname())) {
			queryHql.append(" and u.nickname like :nickname");
			params.put("nickname", "%%" + model.getNickname() + "%%");
		}
		
		if(StringUtils.isNotBlank(model.getUsername())) {
			queryHql.append(" and u.username like :username");
			params.put("username", "%%" + model.getUsername() + "%%");
		}
		
		if(StringUtils.isNotBlank(model.getLastLoginTimeBegin())) {
			queryHql.append(" and u.lastLoginTime >= :lastLoginTimeBegin");
			params.put("lastLoginTimeBegin", model.getLastLoginTime());
		}
		
		if(StringUtils.isNotBlank(model.getLastLoginTimeEnd())) {
			queryHql.append(" and u.lastLoginTime <= :lastLoginTimeEnd");
			params.put("lastLoginTimeEnd", model.getLastLoginTimeEnd());
		}
		
		if(StringUtils.isNotBlank(model.getRegisterTimeBegin())) {
			queryHql.append(" and u.registerTime >= :registerTimeBegin");
			params.put("registerTimeBegin", model.getRegisterTimeBegin());
		}
		
		if(StringUtils.isNotBlank(model.getRegisterTimeEnd())) {
			queryHql.append(" and u.registerTime <= :registerTimeEnd");
			params.put("registerTimeEnd", model.getRegisterTimeEnd());
		}
		
		if(StringUtils.isNotBlank(model.getPhoneNum())) {
			queryHql.append(" and u.phoneNum like :phoneNum");
			params.put("phoneNum","%%" + model.getPhoneNum() + "%%");
		}
		
		if(StringUtils.isNotBlank(model.getIdCardNum())) {
			queryHql.append(" and u.idCardNum like :idCardNum");
			params.put("idCardNum", "%%" + model.getIdCardNum() + "%%");
		}
		
		if(StringUtils.isNotBlank(model.getIsValid())) {
			queryHql.append(" and u.isValid like :isValid");
			params.put("isValid", model.getIsValid());
		}
		
		if(model.getBalanceBegin() != 0.0) {
			queryHql.append(" and u.balance >= :balanceBegin");
			params.put("balanceBegin", model.getBalanceBegin());
		}
		
		if(model.getBalanceEnd() != 0.0) {
			queryHql.append(" and u.balance <= :balanceEnd");
			params.put("balanceEnd", model.getBalanceEnd());
		}

		queryHql.append(" order by u.registerTime desc"); // 

		List<FrontUser> list = baseDao.find(queryHql.toString(), params, pageNum, pageSize);

		List<FrontUserModel> userModels = new ArrayList<FrontUserModel>();
		for (FrontUser u : list) {
			FrontUserModel userModel = new FrontUserModel();
			BeanUtils.copyProperties(u, userModel);
			FrontUserModel frontUserModel = getUserById(userModel.getRecommender());
			if (frontUserModel.getUsername() != null) {
				userModel.setRecommender(frontUserModel.getUsername());
			} else {
				userModel.setRecommender("");
			}
			userModels.add(userModel);
		}

		StringBuffer countHql = new StringBuffer("select count(1) ").append(queryHql);
		int allRows = baseDao.count(countHql.toString(), params).intValue();

		return new PageModel(pageNum, pageSize, userModels, allRows);
	}

	@Override
	public void updateUser(FrontUserModel model) {
		FrontUser user = baseDao.get(FrontUser.class, model.getId());

		BeanUtils.copyProperties(model, user, new String[]{"password", "registerTime", "lastLoginTime", "balance"});

		baseDao.update(user);
	}

	@Override
	public void deleteUserById(String id) {
		
		String[] ids = id.split(",");
		
		for(String sigleId : ids) {
			FrontUser user = baseDao.get(FrontUser.class, sigleId);

			baseDao.delete(user);
		}
	}

	@Override
	public void updatePasswordToDefault(String id) {
		FrontUser user = baseDao.get(FrontUser.class, id);

		user.setPassword(Encrypt.md5AndSha("88888888"));

		baseDao.update(user);
	}

	@Override
	public boolean checkFrontUsernameIsExist(String username) {

		Map<String, Object> params = new HashMap<String, Object>();
		
		params.put("username", username);
		
		FrontUser FrontUser = baseDao.get("from FrontUser f where f.username = :username", params);
		
		if(null != FrontUser) {
			return false;
		} else {
			return true;
		}
	}

	
	@Override
	public String getIdByFrontUsername(String username) {
         Map<String, Object> params = new HashMap<String, Object>();
		params.put("username", username);
		FrontUser FrontUser = baseDao.get("from FrontUser f where f.username = :username", params); 
		if(null != FrontUser) {
			return FrontUser.getId();
		} else {
			return "";
		}
	}

	@Override
	public void updateLoginTimeById(String id, String loginTime) {

		FrontUser FrontUser = baseDao.get(FrontUser.class, id);
		
		FrontUser.setLastLoginTime(loginTime);
		
		baseDao.update(FrontUser);
		
	}

	@Override
	public void updateUserBalanceById(FrontUserModel model) {

		FrontUser frontUser = baseDao.get(FrontUser.class, model.getId());
		
		double balance = frontUser.getBalance();
		
		balance += model.getBalance();
		
		frontUser.setBalance(balance);
		
		baseDao.update(frontUser);
	}

	@Override
	public FrontUserModel getUserCount(String id) {
		FrontUser user = baseDao.get(FrontUser.class, id);
		
		FrontUserModel model = new FrontUserModel();
		
		BeanUtils.copyProperties(user, model);
		
		return model;
	}

	@Override
	public boolean validPassword(FrontUserModel model) {

		FrontUser user = baseDao.get(FrontUser.class, model.getId());
		
		if(null != user && user.getPassword().equals(Encrypt.md5AndSha(model.getPassword()))) {
			return true;
		}
		
		return false;
	}

	@Override
	public void updateUserPassword(FrontUserModel model) {

		FrontUser user = baseDao.get(FrontUser.class, model.getId());
		
		user.setPassword(Encrypt.md5AndSha(model.getPassword()));
		
		baseDao.update(user);
	}

	@Override
	public void updateUserStatus(String id, String status) {

		String[] ids = id.split(",");
		
		for(String sigleId : ids) {
			FrontUser user = baseDao.get(FrontUser.class, sigleId);

			user.setIsValid(status);
			
			baseDao.update(user);
		}
		
	}

	@Override
	public void updateUserConsumptionById(FrontUserModel model) {
      FrontUser frontUser = baseDao.get(FrontUser.class, model.getId());
		
		double consumption = frontUser.getConsumption();
		
		consumption += model.getConsumption();
		
		frontUser.setConsumption(consumption);
		
		baseDao.update(frontUser);
	}
	

}
