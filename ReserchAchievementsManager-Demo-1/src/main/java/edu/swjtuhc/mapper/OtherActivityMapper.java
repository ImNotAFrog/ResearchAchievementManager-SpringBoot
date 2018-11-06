package edu.swjtuhc.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import edu.swjtuhc.model.PagingRequestMsg;
import edu.swjtuhc.model.RankingRequestMsg;
import edu.swjtuhc.model.OtherActivity;
@Mapper
public interface OtherActivityMapper {
	Integer createOtherActivity(OtherActivity ra);
	List<OtherActivity> getOtherActivityByAccount(PagingRequestMsg msg);
	OtherActivity getOtherActivityById(Long actId);
	List<OtherActivity> getOtherActivityList(PagingRequestMsg msg);
	Integer updateOtherActivity(OtherActivity ra);
	Integer deleteOtherActivity(Long actId);
	List<OtherActivity> getOtherActivityDuring(RankingRequestMsg msg);
	List<OtherActivity> getPublishedOtherActivityList(PagingRequestMsg msg);
}
