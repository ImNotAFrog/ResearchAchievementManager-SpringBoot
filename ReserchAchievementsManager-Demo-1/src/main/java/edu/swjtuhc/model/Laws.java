package edu.swjtuhc.model;

import java.util.Date;

public class Laws {
	private Long lId;
	private String lName;
	private String lNum;
	private Integer level;
	private Integer involvement;
	private Integer wordsCount;
	private String attachment;
	private String uploader;
	private Date uploadDate;
	private Float score;
	private Float maxScore;
	private String department;
	private String subDepartment;
	public Long getlId() {
		return lId;
	}
	public void setlId(Long lId) {
		this.lId = lId;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	public String getlNum() {
		return lNum;
	}
	public void setlNum(String lNum) {
		this.lNum = lNum;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public Integer getInvolvement() {
		return involvement;
	}
	public void setInvolvement(Integer involvement) {
		this.involvement = involvement;
	}
	public Integer getWordsCount() {
		return wordsCount;
	}
	public void setWordsCount(Integer wordsCount) {
		this.wordsCount = wordsCount;
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
