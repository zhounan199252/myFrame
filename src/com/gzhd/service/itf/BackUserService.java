package com.gzhd.service.itf;

import com.gzhd.model.BackUserModel;
import com.gzhd.model.PageModel;

/**
 * @Description:user 业务逻辑类
 * @time:2015年7月24日 下午3:00:50
 */
public interface BackUserService {
	
	public static final String BEAN_NAME = "com.gzhd.service.itf.BackUserService";

	/**
	 * @Description:根据id获取User对象
	 * @param id
	 * @return
	 * @return:User
	 * @exception:
	 * @time:2015年7月24日 下午3:03:54
	 */
	public BackUserModel getUserById(String id);

	/**
	 * @Description:添加一个用户
	 * @param model
	 * @return
	 * @return:Integer
	 * @exception:
	 * @time:2015年7月24日 下午3:08:55
	 */
	public String addUser(BackUserModel model);

	/**
	 * @Description:用户登录
	 * @param model
	 * @return
	 * @return:UserModel
	 * @exception:
	 * @time:2015年7月24日 下午4:04:26
	 */
	public BackUserModel login(BackUserModel model);

	/**
	 * @Description:根据分页获取数据
	 * @param pageSize   
	 * 			分页大小
	 * @param pageNum
	 * 			当前页
	 * @param model
	 * @return
	 * @return:PageModel
	 * @exception:
	 * @time:2015年7月27日 上午10:14:14
	 */
	public PageModel getForPageModel(int pageNum, int pageSize, BackUserModel model);

	/**
	 * @Description:更新用户信息
	 * @param model
	 * @return:void
	 * @exception:
	 * @time:2015年7月28日 上午9:35:33
	 */
	public void updateUser(BackUserModel model);

	/**
	 * @Description:根据id删除用户
	 * @param id
	 * @return:void
	 * @exception:
	 * @time:2015年7月28日 上午9:46:44
	 */
	public void deleteUserById(String id);

	/**
	 * @Description:初始化密码，默认初始化为88888888
	 * @param id
	 * @return:void
	 * @exception:
	 * @time:2015年7月28日 上午10:07:07
	 */
	public void updatePasswordToDefault(String id);

	public boolean checkBackUsernameIsExist(String username);

	public void updateLoginTimeById(String id, String loginTime);


}
