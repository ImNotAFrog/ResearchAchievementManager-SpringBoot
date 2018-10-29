package edu.swjtuhc.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import edu.swjtuhc.model.Achievement;
import edu.swjtuhc.model.PagingRequestMsg;
import edu.swjtuhc.model.RankingRequestMsg;

@Mapper
public interface AchievementMapper {
	Long createAchievement(Achievement achievement);
	Integer updateAchievement(Achievement achievement);
	List<Achievement> getAchievementListByAccount(PagingRequestMsg msg);
	List<Achievement> getAchievementList(PagingRequestMsg msg);
	List<Achievement> getAchievementListBySubDepartment(PagingRequestMsg msg);
	List<Achievement> getAchievementByName(PagingRequestMsg msg);
	Achievement getAchievementById(Long aId);
	Integer deleteAchievement(Long aId);
	Achievement getNextAchievementId(Integer state);
	Achievement getNextAchievementIdOfType(Achievement achievement);
	List<Achievement> getIndividualRanking(RankingRequestMsg msg);
}
