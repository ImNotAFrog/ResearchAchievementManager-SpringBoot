package edu.swjtuhc.serviceImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import edu.swjtuhc.mapper.ResearchActivityMapper;
import edu.swjtuhc.model.PagingRequestMsg;
import edu.swjtuhc.model.ReformProject;
import edu.swjtuhc.model.ResearchActivity;
import edu.swjtuhc.service.ResearchActivityService;
import edu.swjtuhc.utils.IdWorker;
import edu.swjtuhc.utils.ModelUtil;

@Service
public class ResearchActivityServiceImpl implements ResearchActivityService {

	@Autowired
	ResearchActivityMapper researchActivityMapper;
	
	@Override
	public Long createResearchActivity(String account) {
		// TODO Auto-generated method stub
		Long id = IdWorker.getInstance().nextId();
		ResearchActivity ra = new ResearchActivity();
		ra.setActId(id);
		ra.setApplicant(account);
		ra.setState(1);
		ra.setUploadDate(new Date());
		int i=0;
		try {
			i=researchActivityMapper.createReserchActivity(ra);
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
	public Integer updateResearchActivity(ResearchActivity raNew) {
		// TODO Auto-generated method stub
		ResearchActivity raOld=researchActivityMapper.getResearchActivityById(raNew.getActId());
		if(raOld==null) {
			return 0;
		}
		int i=0;
		if(raOld.getState()<2) {
			raNew = (ResearchActivity) ModelUtil.updateModelByModel(raOld, raNew);
			raNew.setUploadDate(new Date());
			raNew.setState(1);	
			i=researchActivityMapper.updateResearchActivity(raNew);
		}
		return i;
	}

	@Override
	public Integer modifyResearchActivity(ResearchActivity raNew) {
		// TODO Auto-generated method stub
		ResearchActivity raOld=researchActivityMapper.getResearchActivityById(raNew.getActId());
		if(raOld==null) {
			return 0;
		}
		int i=0;
		if(raOld.getState()<3) {
			raNew = (ResearchActivity) ModelUtil.updateModelByModel(raOld, raNew);
			raNew.setUploadDate(new Date());
			raNew.setState(1);	
			i=researchActivityMapper.updateResearchActivity(raNew);
		}
		return i;
	}

	@Override
	public Integer submitResearchActivity(Long actId) {
		// TODO Auto-generated method stub
		ResearchActivity ra = researchActivityMapper.getResearchActivityById(actId);
		int i = 0;
		if(ra.getState()<2) {
			ra.setState(2);
			i=researchActivityMapper.updateResearchActivity(ra);
		}
		return i;
	}

	@Override
	public Integer withdrawResearchActivity(Long actId) {
		// TODO Auto-generated method stub
		ResearchActivity ra = researchActivityMapper.getResearchActivityById(actId);
		int i = 0;
		if(ra.getState()==2) {
			ra.setState(1);
			i=researchActivityMapper.updateResearchActivity(ra);
		}
		return i;
	}

	@Override
	public Integer publishResearchActivity(Long actId) {
		// TODO Auto-generated method stub
		ResearchActivity ra = researchActivityMapper.getResearchActivityById(actId);
		int i = 0;
		if(ra.getState()==2) {
			ra.setState(3);
			i=researchActivityMapper.updateResearchActivity(ra);
		}
		return i;
	}

	@Override
	public Integer rejectResearchActivity(Long actId) {
		// TODO Auto-generated method stub
		ResearchActivity ra = researchActivityMapper.getResearchActivityById(actId);
		int i = 0;
		if(ra.getState()==2) {
			ra.setState(-1);
			i=researchActivityMapper.updateResearchActivity(ra);
		}
		return i;
	}

	@Override
	public Integer deleteResearchActivity(Long actId) {
		// TODO Auto-generated method stub
		
		return researchActivityMapper.deleteResearchActivity(actId);
	}

	@Override
	public ResearchActivity getResearchActivityById(Long actId) {
		// TODO Auto-generated method stub
		return researchActivityMapper.getResearchActivityById(actId);
	}

	@Override
	public List<ResearchActivity> getResearchActivityList(PagingRequestMsg msg) {
		// TODO Auto-generated method stub
		
		return researchActivityMapper.getResearchActivityList(msg);
	}

	@Override
	public List<ResearchActivity> getPublishedResearchActivityList(PagingRequestMsg msg) {
		// TODO Auto-generated method stub
		return researchActivityMapper.getPublishedResearchActivityList(msg);
	}

	@Override
	public List<ResearchActivity> getResearchActivityByAccount(PagingRequestMsg msg) {
		// TODO Auto-generated method stub
		return researchActivityMapper.getResearchActivityByAccount(msg);
	}

	@Override
	public Integer appendAttachment(ResearchActivity ra) {
		// TODO Auto-generated method stub
		return appendAttachment(ra.getActId(), ra.getAttachment());
	}

	@Override
	public Integer removeAttachment(ResearchActivity ra) {
		// TODO Auto-generated method stub
		return removeAttachment(ra.getActId(), ra.getAttachment());
	}
	
	@Transactional(propagation=Propagation.REQUIRES_NEW,isolation=Isolation.SERIALIZABLE)
	private synchronized int appendAttachment(Long actId, String attachment) {
		ResearchActivity ra = researchActivityMapper.getResearchActivityById(actId);
		ra.setAttachment(ModelUtil.appendPath(ra.getAttachment(), attachment));
		int i=0;
		if(researchActivityMapper.updateResearchActivity(ra)==1) {
			i=1;
		}		 
		return i;	
	}

	@Transactional(propagation=Propagation.REQUIRES_NEW,isolation=Isolation.SERIALIZABLE)
	private synchronized int removeAttachment(Long actId, String attachment) {
		ResearchActivity ra = researchActivityMapper.getResearchActivityById(actId);
		ra.setAttachment(ModelUtil.deletePath(ra.getAttachment(), attachment));
		int i=0;
		if(researchActivityMapper.updateResearchActivity(ra)==1) {
			i=1;
		}		
		return i;	
	}

}
