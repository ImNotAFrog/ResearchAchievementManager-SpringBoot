package edu.swjtuhc.mapper;

import org.apache.ibatis.annotations.Mapper;

import edu.swjtuhc.model.Textbook;
@Mapper
public interface TextbookMapper {
	Long createTextbook(Textbook textbook);
	Textbook getTextbookById(Long tbId);
	Integer updateTextbook(Textbook textbook);
	Integer deleteTextbookById(Long tbId);
}
