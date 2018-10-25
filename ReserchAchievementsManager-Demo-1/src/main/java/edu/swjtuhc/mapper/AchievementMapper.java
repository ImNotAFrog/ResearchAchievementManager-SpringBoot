package edu.swjtuhc.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import edu.swjtuhc.model.Achievement;

@Mapper
public interface AchievementMapper {
	Long createAchievement(Achievement achievement);
	Integer updateAchievement(Achievement achievement);
	List<Achievement> getAchievementListByAccount(HashMap map);
	List<Achievement> getAchievementList(HashMap map);
	List<Achievement> getAchievementListBySubDepartment(HashMap map);
	List<Achievement> getAchievementByName(HashMap map);
	Achievement getAchievementById(Long aId);
	Integer deleteAchievement(Long aId);
	Achievement getNextAchievementId(Integer state);
	Achievement getNextAchievementIdOfType(Achievement achievement);
}
