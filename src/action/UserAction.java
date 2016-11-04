package action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;

import service.UserService;

import com.google.gson.JsonObject;
import com.opensymphony.xwork2.ActionContext;

import domain.UserModel;

/**
 * @Description:user 的action处理类
 * @author:黄威生
 * @time:2015年7月28日 上午10:05:24
 */
@Scope("prototype")
@Action(value = "user", results = { //
@Result(name = "toIndex", location = "/WEB-INF/jsp/Main.jsp"),//
		@Result(name = "list", location = "/WEB-INF/jsp/user/userList.jsp"), //
		@Result(name = "toList", location = "user!list.action", type = "redirectAction"),//
		@Result(name = "add", location = "/WEB-INF/jsp/user/addUser.jsp"),//
		@Result(name = "edit", location = "/WEB-INF/jsp/user/editUser.jsp"),//
		@Result(name = "set", location = "/WEB-INF/jsp/user/setRole.jsp"), //
		@Result(name = "toOaIndex", location = "/WEB-INF/jsp/oaIndex.jsp"), //
		@Result(name = "toeditpw", location = "/WEB-INF/jsp/user/editpassword.jsp") //
})

public class UserAction extends BaseAction<UserModel> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger.getLogger(UserAction.class);

	@Resource
	UserService    userService; 

	
	public void add() {

		userService.addUser(model);

		System.out.print(11);
	}

	
}
