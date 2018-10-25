package edu.swjtuhc.service;

import java.util.List;

import edu.swjtuhc.model.Achievement;

public interface AchievementService {

	List<Achievement> getAchievementListByAccount(String account,Integer start,Integer pageSize);
	List<Achievement> getAchievementList(Integer start,Integer pageSize);
	List<Achievement> getAchievementListBySubDepartment(String subDepartment,Integer start,Integer pageSize);
	List<Achievement> getAchievementByName(String name,Integer start,Integer pageSize);
	Integer submit(Long aId);
	Integer precheck(Long aId);
	Integer approve(Long aId);
	Integer admin1Reject(Long aId);
	Integer admin2Reject(Long aId);
	Integer approvedWithdraw(Long aId);
	Integer preCheckedWithdraw(Long aId);
	Integer submitedWithdraw(Long aId);
	Achievement getNextAchievementId(Integer state);
	Achievement getNextAchievementIdOfType(Achievement achievement);
}
