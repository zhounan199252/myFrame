package action;

import java.io.ByteArrayInputStream;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import util.RandomCodeUtil;
import com.opensymphony.xwork2.ActionContext;
import commom.ConstantValues;

/**
 * @Description:登陆页面以此获取验证码
 */
@Action(value="securityCode", results = {
		@Result(name = "success", type="stream", params = {"contentType","image/jpeg","inputName","inputStream"})})  //params配置：
@Scope("prototype")
public class SecurityCodeAction extends BaseAction<Object> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private ByteArrayInputStream inputStream;
	private String  d;

	public String getCode() throws Exception {
		
		RandomCodeUtil rdnu = RandomCodeUtil.Instance();
		
		this.setInputStream(rdnu.getImage());// 取得带有随机字符串的图片
		
		ActionContext.getContext().getSession().put(ConstantValues.SECURITY_CODE, rdnu.getString());// 取得随机字符串放入HttpSession
		
		return SUCCESS;
	}

	public void setInputStream(ByteArrayInputStream inputStream) {
		this.inputStream = inputStream;
	}

	public ByteArrayInputStream getInputStream() {
		return inputStream;
	}

	public String getD() {
		return d;
	}

	public void setD(String d) {
		this.d = d;
	}
	
	
}
