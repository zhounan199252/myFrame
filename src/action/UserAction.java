package action;

import java.io.UnsupportedEncodingException;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import service.UserService;
import com.google.gson.JsonObject;
import com.opensymphony.xwork2.ActionContext;
import commom.ConstantValues;
import domain.UserModel;

/**
 * @Description:user 的action处理类
 */
@Scope("prototype")
@Action(value = "user", results = { //
@Result(name = "toIndex", location = "/WEB-INF/jsp/index.jsp")
})

public class UserAction extends BaseAction<UserModel> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger.getLogger(UserAction.class);

	@Resource
	UserService    userService; 

	
	/**
	 * @Description:进入主页
	 */
	public String toIndex() {
		return  "toIndex";
	}
	
	
	/**
	 * @Description:用户注册
	 */
	public void register() throws UnsupportedEncodingException {
		String username = ServletActionContext.getRequest().getParameter("username");
		username = new String(username.getBytes("iso8859-1"),"utf-8");
		String name = ServletActionContext.getRequest().getParameter("name");
		name = new String(name.getBytes("iso8859-1"),"utf-8");
		model.setName(name);
		model.setUsername(username);
		String str=userService.addUser(model);	
		if(str!=null){
			writeDataToJsp("注册成功");
		}else{
			writeDataToJsp("注册失败");
		}
	
	}

	/**
	 * @Description:用户登录
	 */
	public void login() {

		JsonObject json = new JsonObject();
			UserModel u = userService.findUser(model);

			if (null == u) {
				json.addProperty("success", false);
				json.addProperty("message", "用户名或密码错误，请刷新后重新登陆！");
			} else {
				json.addProperty("success", true);
				json.addProperty("message", "用户名登录成功");
				json.addProperty("object", "user!toIndex.action");
				// 若用户已经登陆，则移除登陆信息
				if (null != ActionContext.getContext().getSession().get(ConstantValues.CURRENT_USER_LOGIN)) {
					ActionContext.getContext().getSession().remove(ConstantValues.CURRENT_USER_LOGIN);
				}
				ActionContext.getContext().getSession().put(ConstantValues.CURRENT_USER_LOGIN, u);

			}


		writeDataToJsp(json.toString());
	}
	
	
	/**
	 * @Description:用户唯一性验证
	 */
	public void unique() {
            String  result="";
			UserModel u = userService.unique(model);
			if (null == u) {
				result="true";
			}else{
				result="false";
			}
		
		writeDataToJsp(result);
	}
	
	
}
