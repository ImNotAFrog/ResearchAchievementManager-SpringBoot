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
import edu.swjtuhc.mapper.TextbookMapper;
import edu.swjtuhc.model.Achievement;
import edu.swjtuhc.model.Textbook;
import edu.swjtuhc.model.UserProfile;
import edu.swjtuhc.service.TextbookService;
import edu.swjtuhc.utils.IdWorker;
import edu.swjtuhc.utils.ModelUtil;

@Service
public class TextbookServiceImpl implements TextbookService {

	@Autowired
	TextbookMapper textbookMapper;
	
	@Autowired
	AchievementMapper achievementMapper;
	
	@Override
	public Long createTextbook(String account) throws IOException {
		// TODO Auto-generated method stub
		Textbook textbook = new Textbook();
		textbook.setUploader(account);
		textbook.setUploadDate(new Date());
		textbook.setTbId(IdWorker.getInstance().nextId());
		textbook.setScore(0f);
		textbook.setMaxScore(0f);
		Achievement achievement=new Achievement();
		achievement.setaId(textbook.getTbId());
		achievement.setUploader(account);
		achievement.setUploadDate(textbook.getUploadDate());
		achievement.setType("textbook");
		achievement.setState(1);
		int i =createTextbook(textbook,achievement);
		if(i==1) {
			return textbook.getTbId();
		}else {
			return 0L;
		}
		
	}

	@Override
	public Textbook getTextbookById(Long tbId)  {
		// TODO Auto-generated method stub
		return textbookMapper.getTextbookById(tbId);
	}

	@Override
	public Integer updateTextbook(Textbook textbook,UserProfile user) throws IOException {
		// TODO Auto-generated method stub
		Textbook t = getTextbookById(textbook.getTbId());
		if(t==null) {
			return 0;
		}
		Achievement achievement= achievementMapper.getAchievementById(t.getTbId());
		if(achievement==null||achievement.getState()>1) {
			return 0;
		}
		t = (Textbook) ModelUtil.updateModelByModel(t, textbook);
		t.setDepartment(user.getDepartment());
		t.setSubDepartment(user.getSubDepartment());
		t.setUploader(user.getAccount());
		t.setUploadDate(new Date());
		Map<String,Float> scores = ModelUtil.caculateScore(t);
		t.setScore(scores.get("score"));
		t.setMaxScore(scores.get("maxScore"));
		achievement.setDepartment(t.getDepartment());
		achievement.setSubDepartment(t.getSubDepartment());
		achievement.setUploadDate(t.getUploadDate());
		achievement.setName(t.getTbName());
		achievement.setState(1);
		int i = updateTextbook(t, achievement);
		
		return i;
	}

	@Override
	public Integer deleteTextbook(Long tbId) throws IOException {
		// TODO Auto-generated method stub		
		int i = deleteTextbook(tbId, tbId);
		return i;
	}

	@Override
	public Integer modifyTextbook(Textbook textbook) throws IOException {
		// TODO Auto-generated method stub
		Textbook t = getTextbookById(textbook.getTbId());
		if(t==null) {
			return 0;
		}
		t = (Textbook) ModelUtil.updateModelByModel(t, textbook);
		t.setUploadDate(new Date());
		
		Achievement achievement = achievementMapper.getAchievementById(t.getTbId());
		achievement.setName(t.getTbName());
		achievement.setDepartment(t.getDepartment());
		achievement.setSubDepartment(t.getSubDepartment());
		achievement.setUploadDate(t.getUploadDate());
		achievement.setUploader(t.getUploader());
		int i = updateTextbook(t, achievement);
		return i;
	}
	
	@Override
	public Integer appendAttachment(Textbook textbook) {
		// TODO Auto-generated method stub
		return appendAttachment(textbook.getTbId(), textbook.getAttachment());
	}

	@Override
	public Integer removeAttachment(Textbook textbook) {
		// TODO Auto-generated method stub
		return removeAttachment(textbook.getTbId(), textbook.getAttachment());
	}

	@Transactional(isolation=Isolation.SERIALIZABLE)
	private int createTextbook(Textbook textbook, Achievement achievement){
		int i=0;
		if(textbookMapper.createTextbook(textbook)==1) {
			if(achievementMapper.createAchievement(achievement)==1) {
				i=1;
			}
		}
		
		return i;		
	}
	@Transactional(isolation=Isolation.SERIALIZABLE)
	private int updateTextbook(Textbook textbook, Achievement achievement) {
		int i=0;
		if(textbookMapper.updateTextbook(textbook)==1) {
			if(achievementMapper.updateAchievement(achievement)==1) {
				i=1;
			}
		}		
		return i;	
	}
	
	@Transactional(isolation=Isolation.SERIALIZABLE)
	private int deleteTextbook(Long tbId, Long aId) {
		int i=0;
		if(textbookMapper.deleteTextbookById(tbId)==1) {
			if(achievementMapper.deleteAchievement(aId)==1) {
				i=1;
			}
		}		
		return i;	
	}

	@Transactional(propagation=Propagation.REQUIRES_NEW,isolation=Isolation.SERIALIZABLE)
	private synchronized int appendAttachment(Long tId, String attachment) {
		Textbook t = textbookMapper.getTextbookById(tId);
		t.setAttachment(ModelUtil.appendPath(t.getAttachment(), attachment));
		int i=0;
		if(textbookMapper.updateTextbook(t)==1) {
			i=1;
		}		
		return i;	
	}

	@Transactional(propagation=Propagation.REQUIRES_NEW,isolation=Isolation.SERIALIZABLE)
	private synchronized int removeAttachment(Long tId, String attachment) {
		Textbook t = textbookMapper.getTextbookById(tId);
		t.setAttachment(ModelUtil.deletePath(t.getAttachment(), attachment));
		int i=0;
		if(textbookMapper.updateTextbook(t)==1) {
			i=1;
		}		
		return i;	
	}

}
