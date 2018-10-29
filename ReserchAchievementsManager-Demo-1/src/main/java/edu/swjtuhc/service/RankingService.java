package edu.swjtuhc.service;

import java.util.List;

import edu.swjtuhc.model.Achievement;
import edu.swjtuhc.model.RankingRequestMsg;

public interface RankingService {
	List<Achievement> individual(RankingRequestMsg msg);
	List<Achievement> department(RankingRequestMsg msg);
}
