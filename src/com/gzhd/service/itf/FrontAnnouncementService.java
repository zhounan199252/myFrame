package com.gzhd.service.itf;

import com.gzhd.model.AnnouncementModel;
import com.gzhd.model.PageModel;

public interface FrontAnnouncementService {

	public static final String BEAN_NAME = "com.gzhd.service.itf.FrontAnnouncementService";

	public PageModel getForPageModel(int pageNum, int pageSize, AnnouncementModel model);

	public AnnouncementModel getAnnouncementById(String id);

}
