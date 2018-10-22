package edu.swjtuhc.mapper;

import org.apache.ibatis.annotations.Mapper;

import edu.swjtuhc.model.Laws;
@Mapper
public interface LawsMapper {
	Long createLaws(Laws laws);
	Laws getLawsById(Long lId);
	Integer updateLaws(Laws laws);
	Integer deleteLawsById(Long lId);
}
