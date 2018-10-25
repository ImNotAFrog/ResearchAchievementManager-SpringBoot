package edu.swjtuhc.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import edu.swjtuhc.model.Achievement;
import edu.swjtuhc.model.RequestMsg;

@Mapper
public interface AchievementMapper {
	Long createAchievement(Achievement achievement);
	Integer updateAchievement(Achievement achievement);
	List<Achievement> getAchievementListByAccount(RequestMsg msg);
	List<Achievement> getAchievementList(RequestMsg msg);
	List<Achievement> getAchievementListBySubDepartment(RequestMsg msg);
	List<Achievement> getAchievementByName(RequestMsg msg);
	Achievement getAchievementById(Long aId);
	Integer deleteAchievement(Long aId);
	Achievement getNextAchievementId(Integer state);
	Achievement getNextAchievementIdOfType(Achievement achievement);
	Integer getCount();
}
