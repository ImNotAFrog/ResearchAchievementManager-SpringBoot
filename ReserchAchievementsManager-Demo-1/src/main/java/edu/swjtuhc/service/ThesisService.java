package edu.swjtuhc.service;

import edu.swjtuhc.model.Thesis;

public interface ThesisService {
	Long createThesis(String account);
	Thesis getThesisById(Long tId);
	Integer updateThesis(Thesis thesis);
	Integer deleteThesis(Long tId);
}
