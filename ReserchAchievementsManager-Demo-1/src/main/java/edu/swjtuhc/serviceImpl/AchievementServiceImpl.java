package edu.swjtuhc.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.swjtuhc.mapper.AchievementMapper;
import edu.swjtuhc.model.Achievement;
import edu.swjtuhc.service.AchievementService;

@Service
public class AchievementServiceImpl implements AchievementService {
	@Autowired
	AchievementMapper achievementMapper;

	@Override
	public List<Achievement> getAchievementListByAccount(String account) {
		// TODO Auto-generated method stub
		return achievementMapper.getAchievementListByAccount(account);
	}

	@Override
	public List<Achievement> getAchievementList() {
		// TODO Auto-generated method stub
		return achievementMapper.getAchievementList();
	}

	@Override
	public List<Achievement> getAchievementListBySubDepartment(String subDepartment) {
		// TODO Auto-generated method stub
		return achievementMapper.getAchievementListBySubDepartment(subDepartment);
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
	public Integer reject(Long aId) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		Achievement achievement = achievementMapper.getAchievementById(aId);
		int i=0;
		if (achievement != null && achievement.getState() > 1) {
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

}
