package edu.swjtuhc.model;

import java.util.Date;

public class ExportRequestMsg {
	private Date startDate;
	private Date endDate;
	private String department;
	private String subDepartment;
	private String type;
	private Integer level;
	private Integer subject;
	private String idName;//mapper层使用
	
	public String getIdName() {
		return idName;
	}
	public void setIdName(String idName) {
		this.idName = idName;
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
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		switch (type) {
		case "thesis":
			this.idName="t_id";
			break;
		case "textbook":
			this.idName="tb_id";
			break;
		case "project":
			this.idName="p_id";
			break;
		case "reform_project":
			this.idName="r_p_id";
			break;
		case "patent":
			this.idName="pa_id";
			break;
		case "laws":
			this.idName="l_id";
			break;
		default:
			break;
		}
		this.type = type;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public Integer getSubject() {
		return subject;
	}
	public void setSubject(Integer subject) {
		this.subject = subject;
	}
	
	
}
