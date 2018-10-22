package edu.swjtuhc.serviceImpl;

import java.io.IOException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import edu.swjtuhc.mapper.AchievementMapper;
import edu.swjtuhc.mapper.ReformProjectMapper;
import edu.swjtuhc.model.Achievement;
import edu.swjtuhc.model.ReformProject;
import edu.swjtuhc.model.UserProfile;
import edu.swjtuhc.service.ReformProjectService;
import edu.swjtuhc.utils.IdWorker;
import edu.swjtuhc.utils.ModelUtil;

@Service
public class ReformProjectServiceImpl implements ReformProjectService {

	@Autowired
	ReformProjectMapper reformProjectMapper;
	
	@Autowired
	AchievementMapper achievementMapper;
	
	@Override
	public Long createReformProject(String account) throws IOException {
		// TODO Auto-generated method stub
		ReformProject reformProject = new ReformProject();
		reformProject.setUploader(account);
		reformProject.setUploadDate(new Date());
		reformProject.setRpId(IdWorker.getInstance().nextId());
		Achievement achievement=new Achievement();
		achievement.setaId(reformProject.getRpId());
		achievement.setUploader(account);
		achievement.setUploadDate(reformProject.getUploadDate());
		achievement.setState(0);
		achievement.setType("reformProject");
		achievement.setState(1);
		int i =createReformProject(reformProject,achievement);
		if(i==1) {
			return reformProject.getRpId();
		}else {
			return 0L;
		}
		
	}

	@Override
	public ReformProject getReformProjectById(Long pId)  {
		// TODO Auto-generated method stub
		return reformProjectMapper.getReformProjectById(pId);
	}

	@Override
	public Integer updateReformProject(ReformProject reformProject,UserProfile user) throws IOException {
		// TODO Auto-generated method stub
		ReformProject rp = getReformProjectById(reformProject.getRpId());
		if(rp==null) {
			return 0;
		}
		Achievement achievement= achievementMapper.getAchievementById(rp.getRpId());
		if(achievement==null||achievement.getState()>1) {
			return 0;
		}
		rp = (ReformProject) ModelUtil.updateModelByModel(rp, reformProject);
		rp.setDepartment(user.getDepartment());
		rp.setSubDepartment(user.getSubDepartment());
		rp.setUploader(user.getAccount());
		rp.setUploadDate(new Date());
		achievement.setDepartment(rp.getDepartment());
		achievement.setSubDepartment(rp.getSubDepartment());
		achievement.setUploadDate(rp.getUploadDate());
		achievement.setName(rp.getRpName());
		int i = updateReformProject(rp, achievement);
		
		return i;
	}

	@Override
	public Integer deleteReformProject(Long rpId) throws IOException {
		// TODO Auto-generated method stub		
		int i = deleteReformProject(rpId, rpId);
		return i;
	}

	@Override
	public Integer modifyReformProject(ReformProject reformProject) throws IOException {
		// TODO Auto-generated method stub
		ReformProject rp = getReformProjectById(reformProject.getRpId());
		if(rp==null) {
			return 0;
		}
		rp = (ReformProject) ModelUtil.updateModelByModel(rp, reformProject);
		rp.setUploadDate(new Date());
		
		Achievement achievement = achievementMapper.getAchievementById(rp.getRpId());
		achievement.setName(rp.getRpName());
		achievement.setDepartment(rp.getDepartment());
		achievement.setSubDepartment(rp.getSubDepartment());
		achievement.setUploadDate(rp.getUploadDate());
		achievement.setUploader(rp.getUploader());
		int i = updateReformProject(rp, achievement);
		return i;
	}
	
	@Override
	public Integer appendAttachment(ReformProject reformProject) {
		// TODO Auto-generated method stub
		return appendAttachment(reformProject.getRpId(), reformProject.getAttachment());
	}

	@Override
	public Integer removeAttachment(ReformProject reformProject) {
		// TODO Auto-generated method stub
		return removeAttachment(reformProject.getRpId(), reformProject.getAttachment());
	}

	@Transactional(isolation=Isolation.SERIALIZABLE)
	private int createReformProject(ReformProject reformProject, Achievement achievement){
		int i=0;
		if(reformProjectMapper.createReformProject(reformProject)==1) {
			if(achievementMapper.createAchievement(achievement)==1) {
				i=1;
			}
		}
		
		return i;		
	}
	@Transactional(isolation=Isolation.SERIALIZABLE)
	private int updateReformProject(ReformProject reformProject, Achievement achievement) {
		int i=0;
		if(reformProjectMapper.updateReformProject(reformProject)==1) {
			if(achievementMapper.updateAchievement(achievement)==1) {
				i=1;
			}
		}		
		return i;	
	}
	
	@Transactional(isolation=Isolation.SERIALIZABLE)
	private int deleteReformProject(Long pId, Long aId) {
		int i=0;
		if(reformProjectMapper.deleteReformProjectById(pId)==1) {
			if(achievementMapper.deleteAchievement(aId)==1) {
				i=1;
			}
		}		
		return i;	
	}

	@Transactional(propagation=Propagation.REQUIRES_NEW,isolation=Isolation.SERIALIZABLE)
	private synchronized int appendAttachment(Long rpId, String attachment) {
		ReformProject rp = reformProjectMapper.getReformProjectById(rpId);
		rp.setAttachment(ModelUtil.appendPath(rp.getAttachment(), attachment));
		int i=0;
		if(reformProjectMapper.updateReformProject(rp)==1) {
			i=1;
		}		
		return i;	
	}

	@Transactional(propagation=Propagation.REQUIRES_NEW,isolation=Isolation.SERIALIZABLE)
	private synchronized int removeAttachment(Long rpId, String attachment) {
		ReformProject rp = reformProjectMapper.getReformProjectById(rpId);
		rp.setAttachment(ModelUtil.deletePath(rp.getAttachment(), attachment));
		int i=0;
		if(reformProjectMapper.updateReformProject(rp)==1) {
			i=1;
		}		
		return i;	
	}

}
