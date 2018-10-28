package edu.swjtuhc.serviceImpl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.swjtuhc.mapper.AchievementMapper;
import edu.swjtuhc.model.Achievement;
import edu.swjtuhc.model.RequestMsg;
import edu.swjtuhc.service.AchievementService;

@Service
public class AchievementServiceImpl implements AchievementService {
	@Autowired
	AchievementMapper achievementMapper;

	@Override
	public List<Achievement> getAchievementListByAccount(RequestMsg msg) {
		// TODO Auto-generated method stub		
		msg.setStart((msg.getPage()-1)*msg.getLimit());
		return achievementMapper.getAchievementListByAccount(msg);
	}

	@Override
	public List<Achievement> getAchievementList(RequestMsg msg) {
		// TODO Auto-generated method stub	
		msg.setStart((msg.getPage()-1)*msg.getLimit());
		return achievementMapper.getAchievementList(msg);
	}

	@Override
	public List<Achievement> getAchievementListBySubDepartment(RequestMsg msg) {
		// TODO Auto-generated method stub
		msg.setStart((msg.getPage()-1)*msg.getLimit());
		return achievementMapper.getAchievementListBySubDepartment(msg);
	}

	@Override
	public List<Achievement> getAchievementByName(RequestMsg msg) {
		// TODO Auto-generated method stub
//		msg.setStart((msg.getPage()-1)*msg.getLimit());
		return achievementMapper.getAchievementByName(msg);
		
	}
	@Override
	public Integer submit(Long aId) {
		// TODO Auto-generated method stub
		Achievement achievement = achievementMapper.getAchievementById(aId);
		int i=0;
		if (achievement != null && achievement.getState() == 1) {
			achievement.setState(2);
			i = achievementMapper.updateAchievement(achievement);
		}
		return i;
	}

	@Override
	public Integer precheck(Long aId) {
		// TODO Auto-generated method stub
		Achievement achievement = achievementMapper.getAchievementById(aId);
		int i=0;
		if (achievement != null && achievement.getState() == 2) {
			achievement.setState(3);
			i = achievementMapper.updateAchievement(achievement);
		}
		return i;
	}

	@Override
	public Integer approve(Long aId) {
		// TODO Auto-generated method stub
		Achievement achievement = achievementMapper.getAchievementById(aId);
		int i=0;
		if (achievement != null && achievement.getState() == 3) {
			achievement.setState(4);
			i = achievementMapper.updateAchievement(achievement);
		}
		i = achievementMapper.updateAchievement(achievement);
		return i;
	}

	@Override
	public Integer admin1Reject(Long aId) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		Achievement achievement = achievementMapper.getAchievementById(aId);
		int i=0;
		if (achievement != null && achievement.getState() == 3) {
			achievement.setState(-1);
			i = achievementMapper.updateAchievement(achievement);
		}
		
		return i;
	}
	
	@Override
	public Integer admin2Reject(Long aId) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		Achievement achievement = achievementMapper.getAchievementById(aId);
		int i=0;
		if (achievement != null && achievement.getState() == 2) {
			achievement.setState(-1);
			i = achievementMapper.updateAchievement(achievement);
		}
		
		return i;
	}
	@Override
	public Integer approvedWithdraw(Long aId) {
		// TODO Auto-generated method stub
		Achievement achievement = achievementMapper.getAchievementById(aId);
		int i=0;
		if (achievement != null && achievement.getState() == 4) {
			achievement.setState(3);
			i = achievementMapper.updateAchievement(achievement);
		}
		return i;
	}

	@Override
	public Integer preCheckedWithdraw(Long aId) {
		// TODO Auto-generated method stub
		Achievement achievement = achievementMapper.getAchievementById(aId);
		int i=0;
		if (achievement != null && achievement.getState() == 3) {
			achievement.setState(2);
			i = achievementMapper.updateAchievement(achievement);
		}
		return i;
	}

	@Override
	public Integer submitedWithdraw(Long aId) {
		// TODO Auto-generated method stub
		Achievement achievement = achievementMapper.getAchievementById(aId);
		int i=0;
		if (achievement != null && (achievement.getState() == 2||achievement.getState()==3)) {
			achievement.setState(1);
			i = achievementMapper.updateAchievement(achievement);
		}
		return i;
	}


	@Override
	public Achievement getNextAchievementId(Integer state) {
		// TODO Auto-generated method stub
		return achievementMapper.getNextAchievementId(state);
	}

	@Override
	public Achievement getNextAchievementIdOfType(Achievement achievement) {
		// TODO Auto-generated method stub
		return achievementMapper.getNextAchievementIdOfType(achievement);
	}

	@Override
	public Integer getCount() {
		// TODO Auto-generated method stub
		return achievementMapper.getCount();
	}

}
