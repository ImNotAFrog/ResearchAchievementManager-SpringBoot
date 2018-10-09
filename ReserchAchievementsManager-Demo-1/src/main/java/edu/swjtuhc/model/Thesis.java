package edu.swjtuhc.model;

import java.util.Date;

public class Thesis {
	private Long tId;
	private String tName;
	private String journalName;
	private String journalLevel;
	private String journalId;
	private String attachment;
	private String uploader;
	private Date publishDate;
	private Date uploadDate;
	private Float score;
	private Float maxScore;
	private String department;
	private String subDepartment;
	public Thesis() {
		
	}
	public Long gettId() {
		return tId;
	}
	public void settId(Long tId) {
		this.tId = tId;
	}
	public String gettName() {
		return tName;
	}
	public void settName(String tName) {
		this.tName = tName;
	}
	public String getJournalName() {
		return journalName;
	}
	public void setJournalName(String journalName) {
		this.journalName = journalName;
	}
	public String getJournalLevel() {
		return journalLevel;
	}
	public void setJournalLevel(String journalLevel) {
		this.journalLevel = journalLevel;
	}
	public String getJournalId() {
		return journalId;
	}
	public void setJournalId(String journalId) {
		this.journalId = journalId;
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
	public Float getScore() {
		return score;
	}
	public void setScore(Float score) {
		this.score = score;
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
	public Date getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}
	public Date getUploadDate() {
		return uploadDate;
	}
	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}
	public Float getMaxScore() {
		return maxScore;
	}
	public void setMaxScore(Float maxScore) {
		this.maxScore = maxScore;
	}
	
}
