package edu.swjtuhc.service;

import java.util.List;

import edu.swjtuhc.model.PagingRequestMsg;
import edu.swjtuhc.model.ReformProject;
import edu.swjtuhc.model.OtherActivity;

public interface OtherActivityService {
	Long createOtherActivity(String account);
	Integer updateOtherActivity(OtherActivity oa);
	Integer modifyOtherActivity(OtherActivity oa);
	Integer submitOtherActivity(Long actId);
	Integer withdrawOtherActivity(Long actId);
	Integer withdrawPublishedOtherActivity(Long actId);
	Integer publishOtherActivity(Long actId);
	Integer rejectOtherActivity(Long actId);
	Integer deleteOtherActivity(Long actId);
	OtherActivity getOtherActivityById(Long actId);
	List<OtherActivity> getOtherActivityList(PagingRequestMsg msg);
	List<OtherActivity> getOtherActivityByAccount(PagingRequestMsg msg);
	List<OtherActivity> getPublishedOtherActivityList(PagingRequestMsg msg);
	Integer appendAttachment(OtherActivity oa);
	Integer removeAttachment(OtherActivity oa);
}
