package edu.swjtuhc.serviceImpl;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.Date;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import edu.swjtuhc.mapper.AchievementMapper;
import edu.swjtuhc.mapper.ThesisMapper;
import edu.swjtuhc.model.Achievement;
import edu.swjtuhc.model.Thesis;
import edu.swjtuhc.model.UserProfile;
import edu.swjtuhc.service.ThesisService;
import edu.swjtuhc.utils.IdWorker;
import edu.swjtuhc.utils.ModelUtil;

@Service
public class ThesisServiceImpl implements ThesisService{

	@Autowired
	ThesisMapper thesisMapper;
	
	@Autowired
	AchievementMapper achievementMapper;
	
	@Override
	public Long createThesis(String account) throws IOException {
		// TODO Auto-generated method stub
		Thesis thesis = new Thesis();
		thesis.setUploader(account);
		thesis.setUploadDate(new Date());
		thesis.settId(IdWorker.getInstance().nextId());
		Achievement achievement=new Achievement();
		achievement.setaId(thesis.gettId());
		achievement.setUploader(account);
		achievement.setUploadDate(thesis.getUploadDate());
		achievement.setState(0);
		achievement.setType("thesis");
		achievement.setState(1);
		int i =createThesis(thesis,achievement);
		if(i==1) {
			return thesis.gettId();
		}else {
			return 0L;
		}
		
	}

	@Override
	public Thesis getThesisById(Long tId)  {
		// TODO Auto-generated method stub
		return thesisMapper.getThesisById(tId);
	}

	@Override
	public Integer updateThesis(Thesis thesis,UserProfile user) throws IOException {
		// TODO Auto-generated method stub
		Thesis t = getThesisById(thesis.gettId());
		if(t==null) {
			return 0;
		}
		if(thesis.getAttachment()==null) {
			t.setAttachment(null);
		}
		Achievement achievement= achievementMapper.getAchievementById(t.gettId());
		if(achievement==null||achievement.getState()>1) {
			return 0;
		}
		t = (Thesis) ModelUtil.updateModelByModel(t, thesis);
		t.setDepartment(user.getDepartment());
		t.setSubDepartment(user.getSubDepartment());
		t.setUploader(user.getAccount());
		t.setUploadDate(new Date());
		achievement.setDepartment(t.getDepartment());
		achievement.setSubDepartment(t.getSubDepartment());
		achievement.setUploadDate(t.getUploadDate());
		achievement.setName(t.gettName());
		int i = updateThesis(t, achievement);
		
		return i;
	}

	@Override
	public Integer deleteThesis(Long tId) throws IOException {
		// TODO Auto-generated method stub		
		int i = deleteThesis(tId, tId);
		return i;
	}

	@Override
	public Integer modifyThesis(Thesis thesis) throws IOException {
		// TODO Auto-generated method stub
		Thesis t = getThesisById(thesis.gettId());
		if(t==null) {
			return 0;
		}
		if(thesis.getAttachment()==null) {
			t.setAttachment(null);
		}
		t = (Thesis) ModelUtil.updateModelByModel(t, thesis);
		t.setUploadDate(new Date());
		
		Achievement achievement = achievementMapper.getAchievementById(t.gettId());
		achievement.setName(t.gettName());
		achievement.setDepartment(t.getDepartment());
		achievement.setSubDepartment(t.getSubDepartment());
		achievement.setUploadDate(t.getUploadDate());
		achievement.setUploader(t.getUploader());
		int i = updateThesis(t, achievement);
		return i;
	}
	
	@Override
	public Integer appendAttachment(Thesis thesis) {
		// TODO Auto-generated method stub
		return appendAttachment(thesis.gettId(), thesis.getAttachment());
	}



	@Transactional(isolation=Isolation.SERIALIZABLE)
	private int createThesis(Thesis thesis, Achievement achievement){
		int i=0;
		if(thesisMapper.createThesis(thesis)==1) {
			if(achievementMapper.createAchievement(achievement)==1) {
				i=1;
			}
		}
		
		return i;		
	}
	@Transactional(isolation=Isolation.SERIALIZABLE)
	private int updateThesis(Thesis thesis, Achievement achievement) {
		int i=0;
		if(thesisMapper.updateThesis(thesis)==1) {
			if(achievementMapper.updateAchievement(achievement)==1) {
				i=1;
			}
		}		
		return i;	
	}
	
	@Transactional(isolation=Isolation.SERIALIZABLE)
	private int deleteThesis(Long tId, Long aId) {
		int i=0;
		if(thesisMapper.deleteThesisById(tId)==1) {
			if(achievementMapper.deleteAchievement(aId)==1) {
				i=1;
			}
		}		
		return i;	
	}

	@Transactional(propagation=Propagation.REQUIRES_NEW,isolation=Isolation.SERIALIZABLE)
	private synchronized int appendAttachment(Long tId, String attachment) {
		Thesis t = thesisMapper.getThesisById(tId);
		t.setAttachment(ModelUtil.appendPath(t.getAttachment(), attachment));
		int i=0;
		if(thesisMapper.updateThesis(t)==1) {
			i=1;
		}		
		return i;	
	}

	
}
