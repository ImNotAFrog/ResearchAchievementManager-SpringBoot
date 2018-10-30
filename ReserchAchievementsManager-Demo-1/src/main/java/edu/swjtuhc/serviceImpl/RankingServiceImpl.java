package edu.swjtuhc.serviceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.swjtuhc.mapper.AchievementMapper;
import edu.swjtuhc.model.Achievement;
import edu.swjtuhc.model.DepartmentRank;
import edu.swjtuhc.model.IndividualRank;
import edu.swjtuhc.model.RankingRequestMsg;
import edu.swjtuhc.service.RankingService;

@Service
public class RankingServiceImpl implements RankingService {
	@Autowired
	AchievementMapper achievementMapper;

	@Override
	public List<IndividualRank> individual(RankingRequestMsg msg) {
		// TODO Auto-generated method stub
		List<Achievement> list = achievementMapper.getAchievementDuring(msg);
		Map<String, IndividualRank> rankMap = new HashMap<String, IndividualRank>();
		List<IndividualRank> rankList = new ArrayList<>();
		IndividualRank rank = null;
		for (int i = 0; i < list.size(); i++) {
			Achievement a = list.get(i);
			rank = rankMap.get(a.getUploader());
			if (rank == null) {
				rank = new IndividualRank();
				rank.setAccount(a.getUploader());
				rank.setDepartment(a.getDepartment());
				rank.setName(a.getUploaderName());
				rank.setSubDepartment(a.getSubDepartment());
				rank.setScore(a.getScore());
				rank.setCount(1);
				List<Achievement> l = new ArrayList<>();
				l.add(a);
				rank.setAchievementList(l);
			} else {
				List<Achievement> l = rank.getAchievementList();
				l.add(a);
				rank.setAchievementList(l);
				rank.setCount(rank.getCount() + 1);
				rank.setScore(rank.getScore() + a.getScore());
			}
			rankMap.put(a.getUploader(), rank);
		}
		rankList = new ArrayList<>(rankMap.values());
		rankList.sort(new Comparator<IndividualRank>() {

			@Override
			public int compare(IndividualRank o1, IndividualRank o2) {
				// TODO Auto-generated method stub
				if(o1.getScore()>o2.getScore()) {
					return 0;
				}else {
					return 1;
				}
			}
		});
		for (int i = 0; i < rankList.size(); i++) {
			rankList.get(i).setRank(i+1);
		}
		
		return rankList;
	}

	@Override
	public List<DepartmentRank> department(RankingRequestMsg msg) {
		// TODO Auto-generated method stub
		return null;
	}

}
