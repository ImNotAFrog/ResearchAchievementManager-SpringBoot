package edu.swjtuhc.service;

import edu.swjtuhc.model.Thesis;
import edu.swjtuhc.model.UserProfile;

public interface ThesisService {
	Long createThesis(String account);
	Thesis getThesisById(Long tId);
	Integer updateThesis(Thesis thesis,UserProfile user);
	Integer deleteThesis(Long tId);
}
