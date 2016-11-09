package action;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @Description:所有action都必须要继承BaseAction获得ParentPackage和ModelDriven
 * 
 */
@ParentPackage("defaultPackage")
@Namespace("/")
@SuppressWarnings("unchecked")
public abstract class BaseAction<T> extends ActionSupport implements ModelDriven<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = Logger.getLogger(BaseAction.class);
	
	protected T model;
	
	//构造函数生成model实例
	
	@SuppressWarnings("rawtypes")
	public BaseAction() {
		//通过反射得到model类型信息
		ParameterizedType pt =  (ParameterizedType) this.getClass().getGenericSuperclass();
		Class clazz = (Class) pt.getActualTypeArguments()[0];
		
		//通过反射生成model实例
		try {
			model = (T) clazz.newInstance();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	@Override
	public T getModel() {
		return model;
	}

	
	/**
	 * @Description:把数据返回到前台
	 */
	public void writeDataToJsp(String message) {

		try {
			ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
			ServletActionContext.getResponse().getWriter().write(message);
			ServletActionContext.getResponse().getWriter().flush();
			ServletActionContext.getResponse().getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * @Description:把数据以json格式返回到前台
	 */
	public void writeJsonToJsp(Object obj) {
		String json = JSON.toJSONStringWithDateFormat(obj, "yyyy-MM-dd mm:HH:ss");
		try {
			ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
			ServletActionContext.getResponse().getWriter().write(json);
			ServletActionContext.getResponse().getWriter().flush();
			ServletActionContext.getResponse().getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

}
