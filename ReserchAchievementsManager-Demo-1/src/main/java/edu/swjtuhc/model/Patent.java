package edu.swjtuhc.model;

import java.util.Date;

public class Patent {
	private Long paId;
	private String paName;
	private Integer type;
	private Integer authorRank;
	private String paNum;
	private String paOwner;
	private Date publishDate;
	private String attachment;
	private String uploader;
	private Date uploadDate;
	private Float score;
	private Float maxScore;
	private String department;
	private String subDepartment;
	public Long getPaId() {
		return paId;
	}
	public void setPaId(Long paId) {
		this.paId = paId;
	}
	public String getPaName() {
		return paName;
	}
	public void setPaName(String paName) {
		this.paName = paName;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getAuthorRank() {
		return authorRank;
	}
	public void setAuthorRank(Integer authorRank) {
		this.authorRank = authorRank;
	}
	public String getPaNum() {
		return paNum;
	}
	public void setPaNum(String paNum) {
		this.paNum = paNum;
	}
	public String getPaOwner() {
		return paOwner;
	}
	public void setPaOwner(String paOwner) {
		this.paOwner = paOwner;
	}
	public Date getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}
	public String getAttachment() {
		return attachment;
	}
	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}
	public String getUploader() {
		return uploader;
	}
	public void setUploader(String uploader) {
		this.uploader = uploader;
	}
	public Date getUploadDate() {
		return uploadDate;
	}
	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}
	public Float getScore() {
		return score;
	}
	public void setScore(Float score) {
		this.score = score;
	}
	public Float getMaxScore() {
		return maxScore;
	}
	public void setMaxScore(Float maxScore) {
		this.maxScore = maxScore;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getSubDepartment() {
		return subDepartment;
	}
	public void setSubDepartment(String subDepartment) {
		this.subDepartment = subDepartment;
	}
	
	
}
