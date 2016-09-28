
package com.gzhd.web.action;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;

import com.gzhd.common.ConstantValues;
import com.gzhd.model.FootballBetModel;
import com.gzhd.model.FrontUserModel;
import com.gzhd.model.PageModel;
import com.gzhd.service.itf.FootballBetService;
import com.gzhd.util.FormatUtil;
import com.gzhd.util.TimeUtil;
import com.opensymphony.xwork2.ActionContext;

@Action(value = "footballBet", results = { //
		@Result(name = "toMatch", location = "match!toMatchIndex.action?message=${message}", type = "redirectAction"), //
		@Result(name = "toList", location = "footballBet!listFootballBet.action", type = "redirectAction"), //
		@Result(name = "toListBasketball", location = "footballBet!listBasketballBet.action", type = "redirectAction"), //
		@Result(name = "list", location = "/WEB-INF/pages/back_page/football/footballList.jsp"), //
		@Result(name = "listBasketball", location = "/WEB-INF/pages/back_page/basketball/basketballList.jsp"),//
		@Result(name = "listMyBasketballBetRecord", location = "/WEB-INF/pages/front_page/betRecord/basketballBet.jsp"),//
		@Result(name = "listMyFootballBetRecord", location = "/WEB-INF/pages/front_page/betRecord/footballBet.jsp")//
})
@Scope("prototype")
public class FootballBetAction extends BaseAction<FootballBetModel> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger.getLogger(FootballBetAction.class);

	@Resource(name = FootballBetService.BEAN_NAME)
	private FootballBetService service;

	public String addFootballBet() {

		FrontUserModel userModel = (FrontUserModel) ActionContext.getContext().getSession().get(ConstantValues.FRONT_CURRENT_USER_LOGIN);

		String curtime = TimeUtil.getCurDate("yyyy-MM-dd HH:mm:ss");

		service.addFootballBet(model, userModel.getId(), curtime);

		model.setMessage("投注成功！");

		return "toMatch";
	}

	public String listFootballBet() {

		model.setMatchType("football");

		PageModel pageModel = service.getForPageModel(model.getPageNum(), ConstantValues.PAGE_SIZE, model);

		ActionContext.getContext().put("pageModel", pageModel);

		return "list";
	}

	public void countBonusBySeriesNum() {

		Double bonus = service.countBonusBySeriesNum(model.getSeriesNum());

		Map<String, String> result = new HashMap<String, String>();

		if (bonus == 0) {
			result.put("success", "false");
		} else {
			result.put("success", "true");
			result.put("bonus", FormatUtil.amountFormatII(bonus));
		}

		writeJsonToJsp(result);
	}

	public String listBasketballBet() {

		model.setMatchType("basketball");

		PageModel pageModel = service.getForPageModel(model.getPageNum(), ConstantValues.PAGE_SIZE, model);

		ActionContext.getContext().put("pageModel", pageModel);

		return "listBasketball";
	}

	public String doFulfil() {

		service.updateFulfilStatus(model.getId());

		return "toList";
	}

	public String deleteFootballBet() {

		service.deleteFootballBet(model.getId());

		return "toList";
	}

	public String fulfilBySeriesNum() {

		service.updateFulfilBySeriesNum(model.getSeriesNum());

		return "toList";
	}

	public String doFulfilForBasketball() {

		service.updateFulfilStatus(model.getId());

		return "toListBasketball";
	}

	public String deleteBasketballBet() {

		service.deleteFootballBet(model.getId());

		return "toListBasketball";
	}

	public String fulfilBySeriesNumForBasketball() {

		service.updateFulfilBySeriesNum(model.getSeriesNum());

		return "toListBasketball";
	}

	
	public String listMyFootballBetRecord() {
		
		model.setMatchType("football");
		
		FrontUserModel userModel = (FrontUserModel) ActionContext.getContext().getSession().get(ConstantValues.FRONT_CURRENT_USER_LOGIN);

		model.setUserId(userModel.getId());

		PageModel pageModel = service.getForPageModel(model.getPageNum(), ConstantValues.PAGE_SIZE, model);

		ActionContext.getContext().put("pageModel", pageModel);
		
		return "listMyFootballBetRecord";
	}
	
	
	public String listMyBasketballBetRecord() {
		
		model.setMatchType("basketball");
		
		FrontUserModel userModel = (FrontUserModel) ActionContext.getContext().getSession().get(ConstantValues.FRONT_CURRENT_USER_LOGIN);

		model.setUserId(userModel.getId());

		PageModel pageModel = service.getForPageModel(model.getPageNum(), ConstantValues.PAGE_SIZE, model);

		ActionContext.getContext().put("pageModel", pageModel);
		
		return "listMyBasketballBetRecord";
	}
}
