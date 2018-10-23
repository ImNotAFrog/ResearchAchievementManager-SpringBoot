package edu.swjtuhc.serviceImpl;

import java.io.IOException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import edu.swjtuhc.mapper.AchievementMapper;
import edu.swjtuhc.mapper.PatentMapper;
import edu.swjtuhc.model.Achievement;
import edu.swjtuhc.model.Patent;
import edu.swjtuhc.model.UserProfile;
import edu.swjtuhc.service.PatentService;
import edu.swjtuhc.utils.IdWorker;
import edu.swjtuhc.utils.ModelUtil;

@Service
public class PatentServiceImpl implements PatentService {

	@Autowired
	PatentMapper patentMapper;
	
	@Autowired
	AchievementMapper achievementMapper;
	
	@Override
	public Long createPatent(String account) throws IOException {
		// TODO Auto-generated method stub
		Patent patent = new Patent();
		patent.setUploader(account);
		patent.setUploadDate(new Date());
		patent.setPaId(IdWorker.getInstance().nextId());
		Achievement achievement=new Achievement();
		achievement.setaId(patent.getPaId());
		achievement.setUploader(account);
		achievement.setUploadDate(patent.getUploadDate());
		achievement.setState(0);
		achievement.setType("patent");
		achievement.setState(1);
		int i =createPatent(patent,achievement);
		if(i==1) {
			return patent.getPaId();
		}else {
			return 0L;
		}
		
	}

	@Override
	public Patent getPatentById(Long paId)  {
		// TODO Auto-generated method stub
		return patentMapper.getPatentById(paId);
	}

	@Override
	public Integer updatePatent(Patent patent,UserProfile user) throws IOException {
		// TODO Auto-generated method stub
		Patent p = getPatentById(patent.getPaId());
		if(p==null) {
			return 0;
		}
		Achievement achievement= achievementMapper.getAchievementById(p.getPaId());
		if(achievement==null||achievement.getState()>1) {
			return 0;
		}
		p = (Patent) ModelUtil.updateModelByModel(p, patent);
		p.setDepartment(user.getDepartment());
		p.setSubDepartment(user.getSubDepartment());
		p.setUploader(user.getAccount());
		p.setUploadDate(new Date());
		achievement.setDepartment(p.getDepartment());
		achievement.setSubDepartment(p.getSubDepartment());
		achievement.setUploadDate(p.getUploadDate());
		achievement.setName(p.getPaName());
		int i = updatePatent(p, achievement);
		
		return i;
	}

	@Override
	public Integer deletePatent(Long paId) throws IOException {
		// TODO Auto-generated method stub		
		int i = deletePatent(paId, paId);
		return i;
	}

	@Override
	public Integer modifyPatent(Patent patent) throws IOException {
		// TODO Auto-generated method stub
		Patent p = getPatentById(patent.getPaId());
		if(p==null) {
			return 0;
		}
		p = (Patent) ModelUtil.updateModelByModel(p, patent);
		p.setUploadDate(new Date());
		
		Achievement achievement = achievementMapper.getAchievementById(p.getPaId());
		achievement.setName(p.getPaName());
		achievement.setDepartment(p.getDepartment());
		achievement.setSubDepartment(p.getSubDepartment());
		achievement.setUploadDate(p.getUploadDate());
		achievement.setUploader(p.getUploader());
		int i = updatePatent(p, achievement);
		return i;
	}
	
	@Override
	public Integer appendAttachment(Patent patent) {
		// TODO Auto-generated method stub
		return appendAttachment(patent.getPaId(), patent.getAttachment());
	}

	@Override
	public Integer removeAttachment(Patent patent) {
		// TODO Auto-generated method stub
		return removeAttachment(patent.getPaId(), patent.getAttachment());
	}

	@Transactional(isolation=Isolation.SERIALIZABLE)
	private int createPatent(Patent patent, Achievement achievement){
		int i=0;
		if(patentMapper.createPatent(patent)==1) {
			if(achievementMapper.createAchievement(achievement)==1) {
				i=1;
			}
		}
		
		return i;		
	}
	@Transactional(isolation=Isolation.SERIALIZABLE)
	private int updatePatent(Patent patent, Achievement achievement) {
		int i=0;
		if(patentMapper.updatePatent(patent)==1) {
			if(achievementMapper.updateAchievement(achievement)==1) {
				i=1;
			}
		}		
		return i;	
	}
	
	@Transactional(isolation=Isolation.SERIALIZABLE)
	private int deletePatent(Long paId, Long aId) {
		int i=0;
		if(patentMapper.deletePatentById(paId)==1) {
			if(achievementMapper.deleteAchievement(aId)==1) {
				i=1;
			}
		}		
		return i;	
	}

	@Transactional(propagation=Propagation.REQUIRES_NEW,isolation=Isolation.SERIALIZABLE)
	private synchronized int appendAttachment(Long paId, String attachment) {
		Patent p = patentMapper.getPatentById(paId);
		p.setAttachment(ModelUtil.appendPath(p.getAttachment(), attachment));
		int i=0;
		if(patentMapper.updatePatent(p)==1) {
			i=1;
		}		
		return i;	
	}

	@Transactional(propagation=Propagation.REQUIRES_NEW,isolation=Isolation.SERIALIZABLE)
	private synchronized int removeAttachment(Long paId, String attachment) {
		Patent p = patentMapper.getPatentById(paId);
		p.setAttachment(ModelUtil.deletePath(p.getAttachment(), attachment));
		int i=0;
		if(patentMapper.updatePatent(p)==1) {
			i=1;
		}		
		return i;	
	}

}