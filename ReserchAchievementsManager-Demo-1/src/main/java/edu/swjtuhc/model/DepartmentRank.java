package edu.swjtuhc.model;

import java.util.List;
import java.util.Map;

public class DepartmentRank {
	private String department;
	private String subDepartment;
	private Float score;
	private List<Achievement> achievementList;
	private Integer rank;
	private Integer count;
	private Map<String, Float> maxScore;
	
	
	public Map<String, Float> getMaxScore() {
		return maxScore;
	}
	public void setMaxScore(Map<String, Float> maxScore) {
		this.maxScore = maxScore;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
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
	public Float getScore() {
		return score;
	}
	public void setScore(Float score) {
		this.score = score;
	}
	public List<Achievement> getAchievementList() {
		return achievementList;
	}
	public void setAchievementList(List<Achievement> achievementList) {
		this.achievementList = achievementList;
	}
	public Integer getRank() {
		return rank;
	}
	public void setRank(Integer rank) {
		this.rank = rank;
	}
	
}
