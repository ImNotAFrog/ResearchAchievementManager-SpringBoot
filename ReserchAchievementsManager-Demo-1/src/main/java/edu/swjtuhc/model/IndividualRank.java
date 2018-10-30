package edu.swjtuhc.model;

import java.util.List;

public class IndividualRank{
	private String account;
	private String name;
	private Float score;
	private String department;
	private String subDepartment;
	private Integer rank;
	private List<Achievement> achievementList;
	private Integer count;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
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
	public Integer getRank() {
		return rank;
	}
	public void setRank(Integer rank) {
		this.rank = rank;
	}
	public List<Achievement> getAchievementList() {
		return achievementList;
	}
	public void setAchievementList(List<Achievement> achievementList) {
		this.achievementList = achievementList;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}

	
	
	
}
