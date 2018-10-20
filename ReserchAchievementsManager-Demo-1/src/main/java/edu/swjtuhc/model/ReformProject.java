package edu.swjtuhc.model;

import java.util.Date;

public class ReformProject {
	private Long rPId;
	private String rPName;
	private Integer involvement;
	private Integer status;
	private Date projectDate; 
	private String attachment;
	private String uploader;
	private Date uploadDate;
	private Float score;
	private Float maxScore;
	private String department;
	private String subDepartment;
	public String getrPName() {
		return rPName;
	}
	public void setrPName(String rPName) {
		this.rPName = rPName;
	}
	public Integer getInvolvement() {
		return involvement;
	}
	public void setInvolvement(Integer involvement) {
		this.involvement = involvement;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getProjectDate() {
		return projectDate;
	}
	public void setProjectDate(Date projectDate) {
		this.projectDate = projectDate;
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
