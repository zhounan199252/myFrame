package com.gzhd.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.gzhd.dao.itf.BaseDao;
import com.gzhd.domain.BackUser;
import com.gzhd.model.BackUserModel;
import com.gzhd.model.PageModel;
import com.gzhd.service.itf.BackUserService;
import com.gzhd.util.Encrypt;

/**
 * @Description:user的业务逻辑实现类
 * @time:2015年7月28日 上午10:07:58
 */
@Service(BackUserService.BEAN_NAME)
public class BackUserServiceImpl implements BackUserService {

	private static final Logger logger = Logger.getLogger(BackUserServiceImpl.class);

	@Resource(name = BaseDao.BEAN_NAME)
	private BaseDao<BackUser> baseDao;

	@Override
	public BackUserModel getUserById(String id) {
		BackUser u = baseDao.get(BackUser.class, id);
		BackUserModel model = new BackUserModel();
		BeanUtils.copyProperties(u, model);
		return model;
	}

	@Override
	public String addUser(BackUserModel model) {
		BackUser user = new BackUser();

		BeanUtils.copyProperties(model, user, new String[] { "password" });
		user.setPassword(Encrypt.md5AndSha(model.getPassword())); // 密码采用md5加sha双重加密

		Serializable s = baseDao.save(user);
		
		return (String) s;
	}

	@Override
	public BackUserModel login(BackUserModel model) {
		Map<String, Object> params = new HashMap<String, Object>();

		params.put("username", model.getUsername());
		params.put("password", Encrypt.md5AndSha(model.getPassword()));

		BackUser user = baseDao.get("from BackUser u where u.username = :username and u.password = :password", params);

		if (null == user) {
			return null;
		}

		BackUserModel u = new BackUserModel();
		BeanUtils.copyProperties(user, u);

		return u;
	}

	@Override
	public PageModel getForPageModel(int pageNum, int pageSize, BackUserModel model) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer queryHql = new StringBuffer("from BackUser u where ");

		queryHql.append(" u.username != 'admin' order by u.id desc"); // 为了系统的安全性，超级管理员不允许任人进行操作。

		List<BackUser> list = baseDao.find(queryHql.toString(), params, pageNum, pageSize);

		List<BackUserModel> userModels = new ArrayList<BackUserModel>();
		for (BackUser u : list) {
			BackUserModel userModel = new BackUserModel();
			BeanUtils.copyProperties(u, userModel);
			userModels.add(userModel);
		}

		StringBuffer countHql = new StringBuffer("select count(1) ").append(queryHql);
		int allRows = baseDao.count(countHql.toString(), params).intValue();

		return new PageModel(pageNum, pageSize, list, allRows);
	}

	@Override
	public void updateUser(BackUserModel model) {
		BackUser user = baseDao.get(BackUser.class, model.getId());

		BeanUtils.copyProperties(model, user, new String[]{"password", "registerTime", "lastLoginTime"});

		baseDao.update(user);
	}

	@Override
	public void deleteUserById(String id) {
		
		String[] ids = id.split(",");
		
		for(String sigleId : ids) {
			BackUser user = baseDao.get(BackUser.class, sigleId);

			baseDao.delete(user);
		}
	}

	@Override
	public void updatePasswordToDefault(String id) {
		BackUser user = baseDao.get(BackUser.class, id);

		user.setPassword(Encrypt.md5AndSha("88888888"));

		baseDao.update(user);
	}

	@Override
	public boolean checkBackUsernameIsExist(String username) {

		Map<String, Object> params = new HashMap<String, Object>();
		
		params.put("username", username);
		
		BackUser backUser = baseDao.get("from BackUser b where b.username = :username", params);
		
		if(null != backUser) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public void updateLoginTimeById(String id, String loginTime) {

		BackUser backUser = baseDao.get(BackUser.class, id);
		
		backUser.setLastLoginTime(loginTime);
		
		baseDao.update(backUser);
		
		
	}

	
}
