package edu.swjtuhc.service;

import java.io.IOException;

import edu.swjtuhc.model.Project;
import edu.swjtuhc.model.UserProfile;

public interface ProjectService {
	Long createProject(String account) throws IOException;
	Project getProjectById(Long tId);
	Integer updateProject(Project project,UserProfile user) throws IOException;
	Integer deleteProject(Long pId) throws IOException;
	Integer modifyProject(Project project) throws IOException;
	Integer appendAttachment(Project project);
	Integer removeAttachment(Project project);
}
