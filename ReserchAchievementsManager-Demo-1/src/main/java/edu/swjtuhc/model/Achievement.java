package edu.swjtuhc.model;

import java.util.Date;

public class Achievement {
	private Long aId;
	private String name;
	private String type;
	private String uploaderName;
	private String uploader;
	private Integer state;
	private Date uploadDate;
	private String department;
	private String subDepartment;
	private Date validDate;
	private Float score;
	private Float maxScore;
	public Achievement() {
		super();
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

	public Long getaId() {
		return aId;
	}
	public void setaId(Long aId) {
		this.aId = aId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUploader() {
		return uploader;
	}
	public void setUploader(String uploader) {
		this.uploader = uploader;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Date getUploadDate() {
		return uploadDate;
	}
	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
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
	public String getUploaderName() {
		return uploaderName;
	}
	public void setUploaderName(String uploaderName) {
		this.uploaderName = uploaderName;
	}
	public Date getValidDate() {
		return validDate;
	}
	public void setValidDate(Date validDate) {
		this.validDate = validDate;
	}
	
}
