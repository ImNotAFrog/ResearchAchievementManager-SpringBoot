package edu.swjtuhc.serviceImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import edu.swjtuhc.mapper.OtherActivityMapper;
import edu.swjtuhc.model.PagingRequestMsg;
import edu.swjtuhc.model.ReformProject;
import edu.swjtuhc.model.OtherActivity;
import edu.swjtuhc.service.OtherActivityService;
import edu.swjtuhc.utils.IdWorker;
import edu.swjtuhc.utils.ModelUtil;

@Service
public class OtherActivityServiceImpl implements OtherActivityService {

	@Autowired
	OtherActivityMapper otherActivityMapper;
	
	@Override
	public Long createOtherActivity(String account) {
		// TODO Auto-generated method stub
		Long id = IdWorker.getInstance().nextId();
		OtherActivity oa = new OtherActivity();
		oa.setActId(id);
		oa.setApplicant(account);
		oa.setState(1);
		oa.setUploadDate(new Date());
		int i=0;
		try {
			i=otherActivityMapper.createOtherActivity(oa);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		if(i==1) {
			return id;			
		}else {
			return (long) i;
		}
	}

	@Override
	public Integer updateOtherActivity(OtherActivity oaNew) {
		// TODO Auto-generated method stub
		OtherActivity oaOld=otherActivityMapper.getOtherActivityById(oaNew.getActId());
		if(oaOld==null) {
			return 0;
		}
		int i=0;
		if(oaOld.getState()<2) {
			oaNew = (OtherActivity) ModelUtil.updateModelByModel(oaOld, oaNew);
			oaNew.setUploadDate(new Date());
			oaNew.setState(1);	
			i=otherActivityMapper.updateOtherActivity(oaNew);
		}
		return i;
	}

	@Override
	public Integer modifyOtherActivity(OtherActivity oaNew) {
		// TODO Auto-generated method stub
		OtherActivity oaOld=otherActivityMapper.getOtherActivityById(oaNew.getActId());
		if(oaOld==null) {
			return 0;
		}
		int i=0;
		if(oaOld.getState()<3) {
			oaNew = (OtherActivity) ModelUtil.updateModelByModel(oaOld, oaNew);
			oaNew.setUploadDate(new Date());
			oaNew.setState(1);	
			i=otherActivityMapper.updateOtherActivity(oaNew);
		}
		return i;
	}

	@Override
	public Integer submitOtherActivity(Long actId) {
		// TODO Auto-generated method stub
		OtherActivity ra = otherActivityMapper.getOtherActivityById(actId);
		int i = 0;
		if(ra.getState()<2) {
			ra.setState(2);
			i=otherActivityMapper.updateOtherActivity(ra);
		}
		return i;
	}

	@Override
	public Integer withdrawOtherActivity(Long actId) {
		// TODO Auto-generated method stub
		OtherActivity oa = otherActivityMapper.getOtherActivityById(actId);
		int i = 0;
		if(oa.getState()==2) {
			oa.setState(1);
			i=otherActivityMapper.updateOtherActivity(oa);
		}
		return i;
	}

	@Override
	public Integer publishOtherActivity(Long actId) {
		// TODO Auto-generated method stub
		OtherActivity oa = otherActivityMapper.getOtherActivityById(actId);
		int i = 0;
		if(oa.getState()==2) {
			oa.setState(3);
			i=otherActivityMapper.updateOtherActivity(oa);
		}
		return i;
	}

	@Override
	public Integer rejectOtherActivity(Long actId) {
		// TODO Auto-generated method stub
		OtherActivity oa = otherActivityMapper.getOtherActivityById(actId);
		int i = 0;
		if(oa.getState()==2) {
			oa.setState(-1);
			i=otherActivityMapper.updateOtherActivity(oa);
		}
		return i;
	}

	@Override
	public Integer deleteOtherActivity(Long actId) {
		// TODO Auto-generated method stub
		
		return otherActivityMapper.deleteOtherActivity(actId);
	}

	@Override
	public OtherActivity getOtherActivityById(Long actId) {
		// TODO Auto-generated method stub
		return otherActivityMapper.getOtherActivityById(actId);
	}

	@Override
	public List<OtherActivity> getOtherActivityList(PagingRequestMsg msg) {
		// TODO Auto-generated method stub
		return otherActivityMapper.getOtherActivityList(msg);
	}

	@Override
	public List<OtherActivity> getPublishedOtherActivityList(PagingRequestMsg msg) {
		// TODO Auto-generated method stub
		return otherActivityMapper.getPublishedOtherActivityList(msg);
	}

	@Override
	public List<OtherActivity> getOtherActivityByAccount(PagingRequestMsg msg) {
		// TODO Auto-generated method stub
		return otherActivityMapper.getOtherActivityByAccount(msg);
	}

	@Override
	public Integer appendAttachment(OtherActivity oa) {
		// TODO Auto-generated method stub
		return appendAttachment(oa.getActId(), oa.getAttachment());
	}

	@Override
	public Integer removeAttachment(OtherActivity oa) {
		// TODO Auto-generated method stub
		return removeAttachment(oa.getActId(), oa.getAttachment());
	}
	
	@Transactional(propagation=Propagation.REQUIRES_NEW,isolation=Isolation.SERIALIZABLE)
	private synchronized int appendAttachment(Long actId, String attachment) {
		OtherActivity oa = otherActivityMapper.getOtherActivityById(actId);
		oa.setAttachment(ModelUtil.appendPath(oa.getAttachment(), attachment));
		int i=0;
		if(otherActivityMapper.updateOtherActivity(oa)==1) {
			i=1;
		}		 
		return i;	
	}

	@Transactional(propagation=Propagation.REQUIRES_NEW,isolation=Isolation.SERIALIZABLE)
	private synchronized int removeAttachment(Long actId, String attachment) {
		OtherActivity oa = otherActivityMapper.getOtherActivityById(actId);
		oa.setAttachment(ModelUtil.deletePath(oa.getAttachment(), attachment));
		int i=0;
		if(otherActivityMapper.updateOtherActivity(oa)==1) {
			i=1;
		}		
		return i;	
	}

	@Override
	public Integer withdrawPublishedOtherActivity(Long actId) {
		// TODO Auto-generated method stub
		OtherActivity oa = otherActivityMapper.getOtherActivityById(actId);
		int i = 0;
		if(oa.getState()==3) {
			oa.setState(2);
			i=otherActivityMapper.updateOtherActivity(oa);
		}
		return i;
	}

}
