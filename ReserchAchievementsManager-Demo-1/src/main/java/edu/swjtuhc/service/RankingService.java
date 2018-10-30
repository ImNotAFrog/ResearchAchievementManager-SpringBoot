package edu.swjtuhc.service;

import java.util.List;

import edu.swjtuhc.model.Achievement;
import edu.swjtuhc.model.DepartmentRank;
import edu.swjtuhc.model.IndividualRank;
import edu.swjtuhc.model.RankingRequestMsg;

public interface RankingService {
	List<IndividualRank> individual(RankingRequestMsg msg);
	List<DepartmentRank> department(RankingRequestMsg msg);
}
