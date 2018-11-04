package edu.swjtuhc.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import edu.swjtuhc.model.RankingRequestMsg;
import edu.swjtuhc.model.ResearchActivity;
@Mapper
public interface ResearchActivityMapper {
	Integer createReserchActivity(ResearchActivity ra);
	List<ResearchActivity> getResearchActivityByAccount(String account);
	ResearchActivity getResearchActivityById(Long actId);
	List<ResearchActivity> getResearchActivityList();
	Integer updateResearchActivity(ResearchActivity ra);
	Integer deleteResearchActivity(Long actId);
	List<ResearchActivity> getResearchActivityDuring(RankingRequestMsg msg);
	List<ResearchActivity> getPublishedResearchActivityList();
}