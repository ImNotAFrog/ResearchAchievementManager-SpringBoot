package edu.swjtuhc.mapper;

import org.apache.ibatis.annotations.Mapper;

import edu.swjtuhc.model.Patent;
@Mapper
public interface PatentMapper {
	Long createPatent(Patent patent);
	Patent getPatentById(Long paId);
	Integer updatePatent(Patent patent);
	Integer deletePatentById(Long paId);
}
