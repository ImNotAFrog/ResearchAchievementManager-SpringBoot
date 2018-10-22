package edu.swjtuhc.service;

import java.io.IOException;

import edu.swjtuhc.model.Textbook;
import edu.swjtuhc.model.UserProfile;

public interface TextbookService {
	Long createTextbook(String account) throws IOException;
	Textbook getTextbookById(Long tbId);
	Integer updateTextbook(Textbook textbook,UserProfile user) throws IOException;
	Integer deleteTextbook(Long tbId) throws IOException;
	Integer modifyTextbook(Textbook textbook) throws IOException;
	Integer appendAttachment(Textbook textbook);
	Integer removeAttachment(Textbook textbook);
}
