package edu.swjtuhc.service;

import java.util.List;

import edu.swjtuhc.model.ResearchActivity;

public interface ResearchActivityService {
	Long createResearchActivity(String account);
	Integer updateResearchActivity(ResearchActivity ra);
	Integer modifyResearchActivity(ResearchActivity ra);
	Integer submitResearchActivity(Long actId);
	Integer withdrawResearchActivity(Long actId);
	Integer publishResearchActivity(Long actId);
	Integer rejectResearchActivity(Long actId);
	Integer deleteResearchActivity(Long actId);
	ResearchActivity getResearchActivityById(Long actId);
	List<ResearchActivity> getResearchActivityList();
	List<ResearchActivity> getResearchActivityByAccount(String account);
	List<ResearchActivity> getPublishedResearchActivityList();

}
