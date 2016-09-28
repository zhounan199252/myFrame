package com.gzhd.web.action;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Map;

import javax.annotation.Resource;



import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.context.annotation.Scope;

import com.gzhd.common.ConstantValues;
import com.gzhd.model.BetMessageModel;
import com.gzhd.model.FrontUserModel;
import com.gzhd.model.PageModel;
import com.gzhd.service.itf.BetMessageService;
import com.gzhd.service.itf.FrontUserService;
import com.gzhd.util.TimeUtil;
import com.opensymphony.xwork2.ActionContext;

@Action(value = "betmessage", results = {
		@Result(name = "toBankRecharge", location = "/WEB-INF/pages/highFrequency/recharge.jsp"),
		@Result(name = "list", location = "/WEB-INF/pages/highFrequency/backBetMessage.jsp"),
		@Result(name = "myCount",location = "frontUser!myCount.action", type = "redirectAction"),
		@Result(name = "toList", location = "betmessage!listBackBetMessage.action", type = "redirectAction") })
@Scope("prototype")
public class BetMessageAction extends BaseAction<BetMessageModel> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//private static final Logger logger = Logger.getLogger(BetMessageAction.class);

	@Resource(name = BetMessageService.BEAN_NAME)
	private BetMessageService betMessageService;
	@Resource(name = FrontUserService.BEAN_NAME)
	private FrontUserService frontUserService;

	public void addBetMessage() {
		String currentTime = TimeUtil.getCurDate("yyyy-MM-dd HH:mm:ss");
		model.setBetDate(currentTime);
		model.setExchangeFlag("待开奖");
		FrontUserModel frontUserModel = frontUserService.getUserById(model
				.getBetPerson());
		if (frontUserModel.getId()!=null) {
			double yue = frontUserModel.getBalance();
			  double amount=0;
			  if(model.getBetChildType().equals("ylsfc-dd")||model.getBetChildType().equals("dd")||model.getBetChildType().equals("pk10dd")||
				 model.getBetChildType().equals("ylsfc-wwdxd")||model.getBetChildType().equals("ylsfc-qwdxd")||
				 model.getBetChildType().equals("ylsfc-bwdxd")||model.getBetChildType().equals("ylsfc-swdxd")||
				 model.getBetChildType().equals("ylsfc-gwdxd")||
				 model.getBetChildType().equals("wwdxd")||model.getBetChildType().equals("qwdxd")
			   ||model.getBetChildType().equals("bwdxd")||model.getBetChildType().equals("swdxd")
			   ||model.getBetChildType().equals("gwdxd")||model.getBetChildType().equals("pk1dxd")
				||model.getBetChildType().equals("pk2dxd")||model.getBetChildType().equals("pk3dxd")||model.getBetChildType().equals("pk4dxd")
				||model.getBetChildType().equals("pk5dxd")||model.getBetChildType().equals("pk6dxd")||model.getBetChildType().equals("pk7dxd")
				||model.getBetChildType().equals("pk8dxd")||model.getBetChildType().equals("pk9dxd")||model.getBetChildType().equals("pk10dxd")){
				  amount = Double.valueOf(model.getBetQuan())*Arrays.asList(model.getBetNum().split(",")).size()/2
				  			* model.getBetPrice();  	
	          }else{
	        	amount = Double.valueOf(model.getBetQuan())
	  			* model.getBetPrice();  
	          }
	    	
			if (yue >= amount) {
				frontUserModel.setBalance(amount*(-1));
				frontUserService.updateUserBalanceById(frontUserModel);
				frontUserModel.setConsumption(amount);
				frontUserService.updateUserConsumptionById(frontUserModel);
				String id = betMessageService.addBetMessage(model);
				if (id != null) {
					writeJsonToJsp("投注成功");
				} else {
					writeJsonToJsp("投注失败，请重新下注");
				}
			} else {
				writeJsonToJsp("金额不足，请联系客服人员充值");
			}

		}

	}
	
	
		
	/**
	 * 账户充值填写页面
	 */
	public String toBankRecharge() {
		return "toBankRecharge";
	}
	
	/**
	 * 账户充值，给用户增加充值金额，返回用户余额页面
	 */
	public String bankRecharge()  {
		
		Map params  =  ActionContext.getContext().getParameters();	
		 String[] result=	(String[]) params.get("paymentResult");	
		try {
			Document doc =  DocumentHelper.parseText(result[0]);
			Element rootElt = doc.getRootElement(); // 获取根节点
			Element gateWayRsp = rootElt.element("GateWayRsp"); 
			Element head = gateWayRsp.element("head"); 
			String  rspCode= head.elementText(("RspCode"));
			
			Element body = gateWayRsp.element("body"); 
			String  amount= body.elementText(("Amount"));
			String  attach= body.elementText(("Attach"));	
	        if(rspCode.equals("000000")){
				String currentTime = TimeUtil.getCurDate("yyyy-MM-dd HH:mm:ss");
				model.setBetDate(currentTime);
				FrontUserModel frontUserModel = frontUserService.getUserById(attach);
				if (frontUserModel.getId()!=null) {
					BigDecimal   b   =   new   BigDecimal(Double.valueOf(amount));
					 double   f1   =   b.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
						frontUserModel.setBalance(f1);
						frontUserService.updateUserBalanceById(frontUserModel);	
	            }
			 
	        }			
			     
		} catch (DocumentException e) {	
			e.printStackTrace();
		}
		 return "myCount";  

	}

	/**
	 * 跳转后台查看用户投注信息界面
	 */

	public String listBackBetMessage() {

		PageModel pageModel = betMessageService.getForPageModel(
				model.getPageNum(), ConstantValues.PAGE_SIZE, model);

		ActionContext.getContext().put("pageModel", pageModel);

		return "list";

	}

	/**
	 * 删除投注信息
	 */
	public String deleteById() {

		betMessageService.deleteById(model.getId());

		return "toList";
	}

	/**
	 * 修改投注信息
	 */

	public String updateBetMessage() {

		betMessageService.updateBetMessage(model);

		return "toList";
	}

}
