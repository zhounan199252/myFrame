
package com.gzhd.web.action;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;

import com.opensymphony.xwork2.ActionContext;

@Action(value = "frontIndex", results = { //
		@Result(name = "toDefault", location = "/WEB-INF/pages/front_page/default.jsp"),//
		@Result(name = "toLogin", location = "/WEB-INF/pages/front_page/login.jsp"),//
		@Result(name = "toBetRecord", location = "/WEB-INF/pages/front_page/myBetRecord.jsp"), //
		@Result(name = "toRegister", location = "/WEB-INF/pages/front_page/register.jsp")//
})
@Scope("prototype")
public class FrontIndexAction extends BaseAction<Object> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger.getLogger(FrontIndexAction.class);
    
	private  String id;
	
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String toDefault() {

		return "toDefault";
	}

	public String toFrontLogin() {

		// des密钥
		ActionContext.getContext().getSession().put("firstKey_f", "-I2Love2Java-");
		ActionContext.getContext().getSession().put("secondKey_f", "-dfxcese23DDFQd3HJGFO?!4saxffsDSs-");
		ActionContext.getContext().getSession().put("thirdKey_f", "!?dfdfDFFFf12FG-");

		return "toLogin";
	}
	
	public String toRegister() {
		// des密钥
		ActionContext.getContext().getSession().put("firstKey_fr", "-I2Love2Jdfdfdava-");
		ActionContext.getContext().getSession().put("secondKey_fr", "-dfxcese23DDgggs2FQd3HJGFO?!4saxffsDSs-");
		ActionContext.getContext().getSession().put("thirdKey_fr", "!?dfdfDFFFf1sdfsds52FG-");
		
		return "toRegister";
	}
	
	public String toBetRecord() {
		
		
		return "toBetRecord";
	}

}






















