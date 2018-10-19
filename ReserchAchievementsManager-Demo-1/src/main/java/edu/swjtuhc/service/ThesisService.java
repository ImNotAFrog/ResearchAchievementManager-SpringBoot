package edu.swjtuhc.service;

import java.io.IOException;

import edu.swjtuhc.model.Thesis;
import edu.swjtuhc.model.UserProfile;

public interface ThesisService {
	Long createThesis(String account) throws IOException;
	Thesis getThesisById(Long tId);
	Integer updateThesis(Thesis thesis,UserProfile user) throws IOException;
	Integer deleteThesis(Long tId) throws IOException;
	Integer modifyThesis(Thesis thesis) throws IOException;
	Integer appendAttachment(Thesis thesis);
}
