package edu.swjtuhc.service;

import java.util.List;

import edu.swjtuhc.model.Achievement;
import edu.swjtuhc.model.RequestMsg;

public interface AchievementService {

	List<Achievement> getAchievementListByAccount(RequestMsg msg);
	List<Achievement> getAchievementList(RequestMsg msg);
	List<Achievement> getAchievementListBySubDepartment(RequestMsg msg);
	List<Achievement> getAchievementByName(RequestMsg msg);
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
	Integer getCount();
}
