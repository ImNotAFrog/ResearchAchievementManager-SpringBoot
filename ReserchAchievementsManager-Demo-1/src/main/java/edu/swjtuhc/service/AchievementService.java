package edu.swjtuhc.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import edu.swjtuhc.model.Achievement;
import edu.swjtuhc.model.ExportRequestMsg;
import edu.swjtuhc.model.PagingRequestMsg;

public interface AchievementService {

	List<Achievement> getAchievementListByAccount(PagingRequestMsg msg);
	List<Achievement> getAchievementList(PagingRequestMsg msg);
	List<Achievement> getAchievementListBySubDepartment(PagingRequestMsg msg);
	List<Achievement> getAchievementByName(PagingRequestMsg msg);
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
	List<Map<String,Object>> getExportAchievementList(ExportRequestMsg msg);
}
