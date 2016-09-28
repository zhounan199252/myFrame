package com.gzhd.web.action;



import java.math.BigDecimal;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;

import com.gzhd.common.ConstantValues;
import com.gzhd.model.DepositApplyModel;
import com.gzhd.model.FrontUserModel;
import com.gzhd.model.PageModel;
import com.gzhd.service.itf.DepositApplyService;
import com.gzhd.service.itf.FrontUserService;
import com.gzhd.util.TimeUtil;
import com.opensymphony.xwork2.ActionContext;


@Action(value = "depositApply", results = {
		@Result(name = "toDepositApply", location = "/WEB-INF/pages/highFrequency/depositApply.jsp"),
		@Result(name = "list", location = "/WEB-INF/pages/highFrequency/depositHandle.jsp"),
		@Result(name = "toList", location = "depositApply!listDepositMessage.action", type = "redirectAction"),
		@Result(name = "toDefault", location = "/WEB-INF/pages/front_page/default.jsp")})
@Scope("prototype")
public class DepositApplyAction extends BaseAction<DepositApplyModel> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Resource(name = DepositApplyService.BEAN_NAME)
	private DepositApplyService depositApplyService;
	@Resource(name = FrontUserService.BEAN_NAME)
	private FrontUserService frontUserService;
	
	
	
	
	/**
	 * 提现申请填写
	 */
	public String toDepositApply() {
		return "toDepositApply";
	}
	
	/**
	 * 提现申请增加
	 */
	
	public String addDepositApply() {
		model.setStatus("待处理");
		model.setApplyTime(TimeUtil.getCurDate("yyyy-MM-dd HH:mm:ss"));
		depositApplyService.addDepositApply(model);
		return "toDefault";
		
	}
	
	/**
	 *  后台提现申请处理
	 */
	public String updateDepositApply() {
		String[] ids = model.getId().split(",");
		for (String sigleId : ids) {			
			DepositApplyModel depositApplyModel=depositApplyService.get(sigleId);
			if(depositApplyModel.getMoney()!=null){
				FrontUserModel frontUserModel = frontUserService.getUserById(depositApplyModel.getUserId());
				if (frontUserModel.getId()!=null&&!depositApplyModel.getStatus().equals("已处理")) {					
					double yue = frontUserModel.getBalance();
					BigDecimal   b   =   new   BigDecimal(Double.valueOf(depositApplyModel.getMoney()));
					 double   f1   =   b.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
					model.setId(sigleId);
					if (yue >= f1) {
							frontUserModel.setBalance(f1*-1);
							frontUserService.updateUserBalanceById(frontUserModel);	
							model.setStatus("已处理");
							
					} else {
						   model.setStatus("余额不足");
					}
					model.setHandletime(TimeUtil.getCurDate("yyyy-MM-dd HH:mm:ss"));
					depositApplyService.updateDepositApply(model);

				
	            }
			}
			
		
		}	
		
		
		return "toList";
		
	}
	
	/**
	 * 后台提现申请查看
	 */

	public String listDepositMessage() {

		PageModel pageModel = depositApplyService.getForPageModel(
				model.getPageNum(), ConstantValues.PAGE_SIZE, model);

		ActionContext.getContext().put("pageModel", pageModel);

		return "list";

	}
	/**
	 * 删除
	 */
	public String deleteById() {

		depositApplyService.deleteById(model.getId());

		return "toList";
	}


}
