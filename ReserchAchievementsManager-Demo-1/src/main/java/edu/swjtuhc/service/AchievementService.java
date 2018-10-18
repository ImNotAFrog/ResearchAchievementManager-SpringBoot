package edu.swjtuhc.service;

import java.util.List;

import edu.swjtuhc.model.Achievement;

public interface AchievementService {

	List<Achievement> getAchievementListByAccount(String account);
	List<Achievement> getAchievementList();
	List<Achievement> getAchievementListBySubDepartment(String subDepartment);
}
