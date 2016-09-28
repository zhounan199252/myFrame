
package com.gzhd.web.action;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;

import com.gzhd.common.ConstantValues;
import com.gzhd.model.AnnouncementModel;
import com.gzhd.model.PageModel;
import com.gzhd.service.itf.AnnouncementService;
import com.gzhd.util.TimeUtil;
import com.opensymphony.xwork2.ActionContext;


@Action(value = "backAnnouncement", results = { //
		@Result(name = "list", location = "/WEB-INF/pages/back_page/announcement/announcementList.jsp"),//
		@Result(name = "toList", location = "backAnnouncement!listAnnouncement.action", type = "redirectAction"),//
		@Result(name = "toAddPage", location = "/WEB-INF/pages/back_page/announcement/addAnnouncement.jsp"),//
		@Result(name = "toEditPage", location = "/WEB-INF/pages/back_page/announcement/editAnnouncement.jsp")//
})
@Scope("prototype")
public class AnnouncementAction extends BaseAction<AnnouncementModel> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger.getLogger(AnnouncementAction.class);
	
	@Resource(name=AnnouncementService.BEAN_NAME)
	private AnnouncementService service;

	public String listAnnouncement() {
		
		PageModel pageModel = service.getForPageModel(model.getPageNum(), ConstantValues.PAGE_SIZE, model);
		
		ActionContext.getContext().put("pageModel", pageModel);
		
		return "list";
		
	}
	
	public String addAnnouncementPage() {
		
		return "toAddPage";
	}
	
	public String addAnnouncement() {
		
		model.setEditTime(TimeUtil.getCurDate("yyyy-MM-dd HH:mm:ss"));
		model.setStatus("no");
		
		String res = service.addAnnouncement(model);
		
		return "toList";
		
	}
	
	public String editAnnouncementPage() {
		
		AnnouncementModel announcementModel = service.getAnnouncementById(model.getId());
		
		ActionContext.getContext().getValueStack().push(announcementModel);
		
		return "toEditPage";
	}
	
	public String editAnnouncement() {
		
		service.editAnnouncement(model);
		
		return "toList";
	}
	
	public String deleteAnnouncement() {
		
		service.deleteAnnouncement(model.getId());
		
		return "toList";
	}
	
	public String publishAnnouncement() {
		
		model.setPublishTime(TimeUtil.getCurDate("yyyy-MM-dd HH:mm:ss"));
		model.setStatus("yes");
		
		service.updateAnnouncementStatus(model);

		return "toList";
	}
	
	public String unPublishAnnouncement() {
		
		model.setPublishTime(TimeUtil.getCurDate(""));
		model.setStatus("no");
		
		service.updateAnnouncementStatus(model);
		
		return "toList";
		
	}
}


























