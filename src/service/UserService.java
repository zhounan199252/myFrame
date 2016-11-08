package service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import dao.BaseDao;
import domain.UserModel;






@Service
public class UserService {
	
	
	@Resource
	private BaseDao<UserModel> baseDao;
	
	
	public String addUser(UserModel model) {
		Serializable s = baseDao.save(model);
		return (String) s;
	}
	
	
	public UserModel findUser(UserModel model) {
		Map<String, Object> params = new HashMap<String, Object>();

		params.put("username", model.getUsername());
		params.put("password", model.getPassword());

		UserModel user = baseDao.get("from UserModel u where u.username = :username and u.password = :password", params);

		if (user!=null) {
			return user;
		}
		
		return null;
	}
	
	
	public UserModel unique(UserModel model) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("username", model.getUsername());
		UserModel user = baseDao.get("from UserModel u where u.username = :username", params);
		if (user!=null) {
			return user;
		}
		return null;
	}
	

}
