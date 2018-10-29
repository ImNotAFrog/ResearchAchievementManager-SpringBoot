package edu.swjtuhc.model;

import java.util.Date;

public class RankingRequestMsg {
	private Date startDate;
	private Date endDate;
	private String department;
	private String subDepartment;
	private String type;
	private Object targetType;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Object getTargetType() {
		return targetType;
	}
	public void setTargetType(Object targetType) {
		this.targetType = targetType;
	}
	
	
}
