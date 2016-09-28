package com.gzhd.model;

public class AnnouncementModel {

	private String id;
	
	private String title;
	
	private String content;
	
	private String publishTime;
	
	private int pageNum = 1;
	
	private String publishTimeBegin;
	
	private String publishTimeEnd;
	
	private String editTime;
	
	private String editTimeBegin;
	
	private String editTimeEnd;
	
	private String status;
	
	private String type;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public String getPublishTimeBegin() {
		return publishTimeBegin;
	}

	public void setPublishTimeBegin(String publishTimeBegin) {
		this.publishTimeBegin = publishTimeBegin;
	}

	public String getPublishTimeEnd() {
		return publishTimeEnd;
	}

	public void setPublishTimeEnd(String publishTimeEnd) {
		this.publishTimeEnd = publishTimeEnd;
	}

	public String getEditTime() {
		return editTime;
	}

	public void setEditTime(String editTime) {
		this.editTime = editTime;
	}

	public String getEditTimeBegin() {
		return editTimeBegin;
	}

	public void setEditTimeBegin(String editTimeBegin) {
		this.editTimeBegin = editTimeBegin;
	}

	public String getEditTimeEnd() {
		return editTimeEnd;
	}

	public void setEditTimeEnd(String editTimeEnd) {
		this.editTimeEnd = editTimeEnd;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
}
