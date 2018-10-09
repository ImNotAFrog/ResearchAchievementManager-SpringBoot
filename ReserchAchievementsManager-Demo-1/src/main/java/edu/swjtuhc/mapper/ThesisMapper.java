package edu.swjtuhc.mapper;

import org.apache.ibatis.annotations.Mapper;

import edu.swjtuhc.model.Thesis;

@Mapper
public interface ThesisMapper {
	Integer createThesis(Thesis thesis);
}
