package edu.swjtuhc.service;

import java.io.IOException;

import edu.swjtuhc.model.Laws;
import edu.swjtuhc.model.UserProfile;

public interface LawsService {
	Long createLaws(String account) throws IOException;
	Laws getLawsById(Long lId);
	Integer updateLaws(Laws laws,UserProfile user) throws IOException;
	Integer deleteLaws(Long lId) throws IOException;
	Integer modifyLaws(Laws laws) throws IOException;
	Integer appendAttachment(Laws laws);
	Integer removeAttachment(Laws laws);
}
