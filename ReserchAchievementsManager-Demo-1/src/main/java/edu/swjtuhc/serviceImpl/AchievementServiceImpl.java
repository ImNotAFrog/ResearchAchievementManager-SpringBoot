package edu.swjtuhc.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.swjtuhc.mapper.AchievementMapper;
import edu.swjtuhc.model.Achievement;
import edu.swjtuhc.model.ExportRequestMsg;
import edu.swjtuhc.model.PagingRequestMsg;
import edu.swjtuhc.service.AchievementService;
import net.sf.ezmorph.object.SwitchingMorpher;

@Service
public class AchievementServiceImpl implements AchievementService {
	@Autowired
	AchievementMapper achievementMapper;

	@Override
	public List<Achievement> getAchievementListByAccount(PagingRequestMsg msg) {
		// TODO Auto-generated method stub
		// msg.setStart((msg.getPage()-1)*msg.getLimit());
		return achievementMapper.getAchievementListByAccount(msg);
	}

	@Override
	public List<Achievement> getAchievementList(PagingRequestMsg msg) {
		// TODO Auto-generated method stub
		// msg.setStart((msg.getPage()-1)*msg.getLimit());
		return achievementMapper.getAchievementList(msg);
	}

	@Override
	public List<Achievement> getAchievementListBySubDepartment(PagingRequestMsg msg) {
		// TODO Auto-generated method stub
		// msg.setStart((msg.getPage()-1)*msg.getLimit());
		return achievementMapper.getAchievementListBySubDepartment(msg);
	}

	@Override
	public List<Achievement> getAchievementByName(PagingRequestMsg msg) {
		// TODO Auto-generated method stub
//		msg.setStart((msg.getPage()-1)*msg.getLimit());
		return achievementMapper.getAchievementByName(msg);

	}

	@Override
	public Integer submit(Long aId) {
		// TODO Auto-generated method stub
		Achievement achievement = achievementMapper.getAchievementById(aId);
		int i = 0;
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
		int i = 0;
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
		int i = 0;
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
		int i = 0;
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
		int i = 0;
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
		int i = 0;
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
		int i = 0;
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
		int i = 0;
		if (achievement != null && (achievement.getState() == 2 || achievement.getState() == 3)) {
			achievement.setState(1);
			i = achievementMapper.updateAchievement(achievement);
		}
		return i;
	}

	@Override
	public Achievement getNextAchievementId(Integer state) {
		// TODO Auto-generated method stub
		Achievement a = achievementMapper.getNextAchievementId(state);
		System.err.println(a.getType());
		return a;
	}

	@Override
	public Achievement getNextAchievementIdOfType(Achievement achievement) {
		// TODO Auto-generated method stub
		return achievementMapper.getNextAchievementIdOfType(achievement);
	}

	@Override
	public List<Map<String, Object>> getExportAchievementList(ExportRequestMsg msg) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = achievementMapper.getExportAchievement(msg);
		List<Map<String, Object>> result = new ArrayList<>();
		Map<String, String> nameMap = new HashMap<String, String>();
		nameMap.put("thesis", "journal_level");
		nameMap.put("textbook", "level");
		nameMap.put("project", "level");
		nameMap.put("patent", "type");
		nameMap.put("laws", "level");
		
		if (msg.getSubject() != null && msg.getType().equals("project")) {
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).get("subject") != null && list.get(i).get("subject").equals(msg.getSubject())) {
					result.add(list.get(i));
				}
			}
			
			if(msg.getLevel()!=null) {
				String level = nameMap.get(msg.getType());
				if (level == null) {
					return null;
				}
				List<Map<String, Object>> result2 = new ArrayList<>();
				for (int i = 0; i < result.size(); i++) {
					if (result.get(i).get(level) != null && result.get(i).get(level).equals(msg.getLevel())) {
						result2.add(result.get(i));
					}
				}
				return result2;
			}			
			return result;
		}
		if (msg.getType() != null && msg.getLevel() != null) {
			String level = nameMap.get(msg.getType());
			if (level == null) {
				return null;
			}
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).get(level) != null && list.get(i).get(level).equals(msg.getLevel())) {
					result.add(list.get(i));
				}
			}

			return result;
		}

		return list;
	}

}
