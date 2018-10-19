package edu.swjtuhc.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import edu.swjtuhc.model.Achievement;

@Mapper
public interface AchievementMapper {
	Long createAchievement(Achievement achievement);
	Integer updateAchievement(Achievement achievement);
	List<Achievement> getAchievementListByAccount(String account);
	List<Achievement> getAchievementList();
	List<Achievement> getAchievementListBySubDepartment(String subDepartment);
	List<Achievement> getAchievementByName(String name);
	Achievement getAchievementById(Long aId);
	Integer deleteAchievement(Long aId);
}
