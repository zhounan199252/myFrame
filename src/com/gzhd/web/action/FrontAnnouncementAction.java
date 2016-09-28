package com.gzhd.web.action;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;

import com.gzhd.common.ConstantValues;
import com.gzhd.model.AnnouncementModel;
import com.gzhd.model.PageModel;
import com.gzhd.service.itf.FrontAnnouncementService;
import com.gzhd.util.SecurityHelper;
import com.opensymphony.xwork2.ActionContext;

@Action(value = "frontAnnouncement", results = { //
		@Result(name = "toAnnouncementList", location = "/WEB-INF/pages/front_page/announcement/announcementList.jsp"), //
		@Result(name = "toFavorList", location = "/WEB-INF/pages/front_page/announcement/favorList.jsp"), //
		@Result(name = "toSingleAnnouncement", location = "/WEB-INF/pages/front_page/announcement/announcement.jsp"), //
		@Result(name = "toSingleFavor", location = "/WEB-INF/pages/front_page/announcement/favor.jsp")//
})
@Scope("prototype")
public class FrontAnnouncementAction extends BaseAction<AnnouncementModel> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Resource(name = FrontAnnouncementService.BEAN_NAME)
	private FrontAnnouncementService service;

	public String announcementList() {

		model.setType("announcement");
		PageModel pageModel = service.getForPageModel(model.getPageNum(), ConstantValues.PAGE_SIZE, model);

		ActionContext.getContext().put("pageModel", pageModel);

		return "toAnnouncementList";
	}
	
	public String favorList() {

		model.setType("favor");
		PageModel pageModel = service.getForPageModel(model.getPageNum(), ConstantValues.PAGE_SIZE, model);

		ActionContext.getContext().put("pageModel", pageModel);

		return "toFavorList";
	}

	public String getSingleAnnouncement() {

		try {
			String id = SecurityHelper.decode(model.getId());
			
			AnnouncementModel announcement = service.getAnnouncementById(id);

			ActionContext.getContext().put("announcement", announcement);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "toSingleAnnouncement";
	}

	public String getSingleFavor() {

		try {
			String id = SecurityHelper.decode(model.getId());

			AnnouncementModel favor = service.getAnnouncementById(id);

			ActionContext.getContext().put("favor", favor);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "toSingleFavor";
	}
}
