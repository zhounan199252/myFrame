package com.gzhd.service.itf;

import com.gzhd.model.AnnouncementModel;
import com.gzhd.model.PageModel;

public interface AnnouncementService {

	public static final String BEAN_NAME = "com.gzhd.service.itf.AnnouncementService";

	public PageModel getForPageModel(int pageNum, int pageSize, AnnouncementModel model);

	public String addAnnouncement(AnnouncementModel model);

	public AnnouncementModel getAnnouncementById(String id);

	public void editAnnouncement(AnnouncementModel model);

	public void deleteAnnouncement(String id);

	public void updateAnnouncementStatus(AnnouncementModel model);

}
