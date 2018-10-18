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

}
