package edu.swjtuhc.serviceImpl;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import edu.swjtuhc.mapper.AchievementMapper;
import edu.swjtuhc.mapper.LawsMapper;
import edu.swjtuhc.model.Achievement;
import edu.swjtuhc.model.Laws;
import edu.swjtuhc.model.UserProfile;
import edu.swjtuhc.service.LawsService;
import edu.swjtuhc.utils.IdWorker;
import edu.swjtuhc.utils.ModelUtil;

@Service
public class LawsServiceImpl implements LawsService {

	@Autowired
	LawsMapper lawsMapper;
	
	@Autowired
	AchievementMapper achievementMapper;
	
	@Override
	public Long createLaws(String account) throws IOException {
		// TODO Auto-generated method stub
		Laws laws = new Laws();
		laws.setUploader(account);
		laws.setUploadDate(new Date());
		laws.setlId(IdWorker.getInstance().nextId());
		laws.setScore(0f);
		laws.setMaxScore(0f);
		Achievement achievement=new Achievement();
		achievement.setaId(laws.getlId());
		achievement.setUploader(account);
		achievement.setUploadDate(laws.getUploadDate());
		achievement.setType("laws");
		achievement.setState(1);
		int i =createLaws(laws,achievement);
		if(i==1) {
			return laws.getlId();
		}else {
			return 0L;
		}
		
	}

	@Override
	public Laws getLawsById(Long lId)  {
		// TODO Auto-generated method stub
		return lawsMapper.getLawsById(lId);
	}

	@Override
	public Integer updateLaws(Laws laws,UserProfile user) throws IOException {
		// TODO Auto-generated method stub
		Laws l = getLawsById(laws.getlId());
		if(l==null) {
			return 0;
		}
		Achievement achievement= achievementMapper.getAchievementById(l.getlId());
		if(achievement==null||achievement.getState()>1) {
			return 0;
		}
		l = (Laws) ModelUtil.updateModelByModel(l, laws);
		l.setDepartment(user.getDepartment());
		l.setSubDepartment(user.getSubDepartment());
		l.setUploader(user.getAccount());
		l.setUploadDate(new Date());
		Map<String,Float> scores = ModelUtil.caculateScore(l);
		l.setScore(scores.get("score"));
		l.setMaxScore(scores.get("maxScore"));
		achievement.setDepartment(l.getDepartment());
		achievement.setSubDepartment(l.getSubDepartment());
		achievement.setUploadDate(l.getUploadDate());
		achievement.setName(l.getlName());
		achievement.setState(1);
		achievement.setValidDate(l.getPublishDate());
		int i = updateLaws(l, achievement);
		
		return i;
	}

	@Override
	public Integer deleteLaws(Long lId) throws IOException {
		// TODO Auto-generated method stub		
		int i = deleteLaws(lId, lId);
		return i;
	}

	@Override
	public Integer modifyLaws(Laws laws) throws IOException {
		// TODO Auto-generated method stub
		Laws l = getLawsById(laws.getlId());
		if(l==null) {
			return 0;
		}
		l = (Laws) ModelUtil.updateModelByModel(l, laws);
		l.setUploadDate(new Date());
		
		Achievement achievement = achievementMapper.getAchievementById(l.getlId());
		achievement.setName(l.getlName());
		achievement.setDepartment(l.getDepartment());
		achievement.setSubDepartment(l.getSubDepartment());
		achievement.setUploadDate(l.getUploadDate());
		achievement.setUploader(l.getUploader());
		achievement.setValidDate(l.getPublishDate());
		int i = updateLaws(l, achievement);
		return i;
	}
	
	@Override
	public Integer appendAttachment(Laws laws) {
		// TODO Auto-generated method stub
		return appendAttachment(laws.getlId(), laws.getAttachment());
	}

	@Override
	public Integer removeAttachment(Laws laws) {
		// TODO Auto-generated method stub
		return removeAttachment(laws.getlId(), laws.getAttachment());
	}

	@Transactional(isolation=Isolation.SERIALIZABLE)
	private int createLaws(Laws laws, Achievement achievement){
		int i=0;
		if(lawsMapper.createLaws(laws)==1) {
			if(achievementMapper.createAchievement(achievement)==1) {
				i=1;
			}
		}
		
		return i;		
	}
	@Transactional(isolation=Isolation.SERIALIZABLE)
	private int updateLaws(Laws laws, Achievement achievement) {
		int i=0;
		if(lawsMapper.updateLaws(laws)==1) {
			if(achievementMapper.updateAchievement(achievement)==1) {
				i=1;
			}
		}		
		return i;	
	}
	
	@Transactional(isolation=Isolation.SERIALIZABLE)
	private int deleteLaws(Long lId, Long aId) {
		int i=0;
		if(lawsMapper.deleteLawsById(lId)==1) {
			if(achievementMapper.deleteAchievement(aId)==1) {
				i=1;
			}
		}		
		return i;	
	}

	@Transactional(propagation=Propagation.REQUIRES_NEW,isolation=Isolation.SERIALIZABLE)
	private synchronized int appendAttachment(Long lId, String attachment) {
		Laws l = lawsMapper.getLawsById(lId);
		l.setAttachment(ModelUtil.appendPath(l.getAttachment(), attachment));
		int i=0;
		if(lawsMapper.updateLaws(l)==1) {
			i=1;
		}		
		return i;	
	}

	@Transactional(propagation=Propagation.REQUIRES_NEW,isolation=Isolation.SERIALIZABLE)
	private synchronized int removeAttachment(Long lId, String attachment) {
		Laws l = lawsMapper.getLawsById(lId);
		l.setAttachment(ModelUtil.deletePath(l.getAttachment(), attachment));
		int i=0;
		if(lawsMapper.updateLaws(l)==1) {
			i=1;
		}		
		return i;	
	}

}
