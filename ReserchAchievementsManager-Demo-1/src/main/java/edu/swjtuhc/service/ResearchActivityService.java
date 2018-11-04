package edu.swjtuhc.service;

import java.util.List;

import edu.swjtuhc.model.PagingRequestMsg;
import edu.swjtuhc.model.ReformProject;
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
	List<ResearchActivity> getResearchActivityList(PagingRequestMsg msg);
	List<ResearchActivity> getResearchActivityByAccount(PagingRequestMsg msg);
	List<ResearchActivity> getPublishedResearchActivityList(PagingRequestMsg msg);
	Integer appendAttachment(ResearchActivity ra);
	Integer removeAttachment(ResearchActivity ra);
}
