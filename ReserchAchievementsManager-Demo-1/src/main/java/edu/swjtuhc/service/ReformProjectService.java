package edu.swjtuhc.service;

import java.io.IOException;

import edu.swjtuhc.model.ReformProject;
import edu.swjtuhc.model.UserProfile;

public interface ReformProjectService {
	Long createReformProject(String account) throws IOException;
	ReformProject getReformProjectById(Long rPId);
	Integer updateReformProject(ReformProject reformProject,UserProfile user) throws IOException;
	Integer deleteReformProject(Long rPId) throws IOException;
	Integer modifyReformProject(ReformProject reformProject) throws IOException;
	Integer appendAttachment(ReformProject reformProject);
	Integer removeAttachment(ReformProject reformProject);
}
