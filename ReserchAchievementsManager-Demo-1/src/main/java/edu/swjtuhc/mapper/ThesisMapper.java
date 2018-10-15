package edu.swjtuhc.mapper;

import org.apache.ibatis.annotations.Mapper;

import edu.swjtuhc.model.Thesis;

@Mapper
public interface ThesisMapper {
	Long createThesis(Thesis thesis);
	Thesis getThesisById(Long tId);
	Integer updateThesis(Thesis thesis);
	Integer deleteThesisById(Long tId);
}
