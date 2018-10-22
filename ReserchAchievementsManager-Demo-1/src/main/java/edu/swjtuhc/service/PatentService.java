package edu.swjtuhc.service;

import java.io.IOException;

import edu.swjtuhc.model.Patent;
import edu.swjtuhc.model.UserProfile;

public interface PatentService {
	Long createPatent(String account) throws IOException;
	Patent getPatentById(Long paId);
	Integer updatePatent(Patent patent,UserProfile user) throws IOException;
	Integer deletePatent(Long paId) throws IOException;
	Integer modifyPatent(Patent patent) throws IOException;
	Integer appendAttachment(Patent patent);
	Integer removeAttachment(Patent patent);
}
