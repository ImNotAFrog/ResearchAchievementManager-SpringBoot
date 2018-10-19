package edu.swjtuhc.service;

import java.util.List;

import edu.swjtuhc.model.Achievement;

public interface AchievementService {

	List<Achievement> getAchievementListByAccount(String account);
	List<Achievement> getAchievementList();
	List<Achievement> getAchievementListBySubDepartment(String subDepartment);
	List<Achievement> getAchievementByName(String name);
	Integer submit(Long aId);
	Integer precheck(Long aId);
	Integer approve(Long aId);
	Integer reject(Long aId);
	Integer approvedWithdraw(Long aId);
	Integer preCheckedWithdraw(Long aId);
	Integer submitedWithdraw(Long aId);
}
