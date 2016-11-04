package service;

import java.io.Serializable;
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
	
	
	

}
