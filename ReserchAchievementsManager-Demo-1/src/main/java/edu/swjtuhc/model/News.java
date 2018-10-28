package edu.swjtuhc.model;

import java.util.Date;

public class News {
	private Long nId;
	private String title;
	private String content;
	private Date uploadDate;
	private String uploader;
	private Integer state;
	private String uploaderName;
	public Long getnId() {
		return nId;
	}
	public void setnId(Long nId) {
		this.nId = nId;
	}
	
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getTitle() {
		return title;
	}
	
	public String getUploaderName() {
		return uploaderName;
	}
	public void setUploaderName(String uploaderName) {
		this.uploaderName = uploaderName;
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
	public Date getUploadDate() {
		return uploadDate;
	}
	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}
	public String getUploader() {
		return uploader;
	}
	public void setUploader(String uploader) {
		this.uploader = uploader;
	}
	
}
