package edu.swjtuhc.mapper;

import org.apache.ibatis.annotations.Mapper;

import edu.swjtuhc.model.ReformProject;
@Mapper
public interface ReformProjectMapper {
	Long createReformProject(ReformProject reformProject);
	ReformProject getReformProjectById(Long rPId);
	Integer updateReformProject(ReformProject reformProject);
	Integer deleteReformProjectById(Long rPId);
}
