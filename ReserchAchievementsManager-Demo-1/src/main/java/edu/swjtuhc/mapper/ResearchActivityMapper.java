package edu.swjtuhc.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import edu.swjtuhc.model.PagingRequestMsg;
import edu.swjtuhc.model.RankingRequestMsg;
import edu.swjtuhc.model.ResearchActivity;
@Mapper
public interface ResearchActivityMapper {
	Integer createResearchActivity(ResearchActivity ra);
	List<ResearchActivity> getResearchActivityByAccount(PagingRequestMsg msg);
	ResearchActivity getResearchActivityById(Long actId);
	List<ResearchActivity> getResearchActivityList(PagingRequestMsg msg);
	Integer updateResearchActivity(ResearchActivity ra);
	Integer deleteResearchActivity(Long actId);
	List<ResearchActivity> getResearchActivityDuring(RankingRequestMsg msg);
	List<ResearchActivity> getPublishedResearchActivityList(PagingRequestMsg msg);
}
