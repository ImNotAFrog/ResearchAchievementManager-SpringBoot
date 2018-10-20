package edu.swjtuhc.mapper;

import org.apache.ibatis.annotations.Mapper;

import edu.swjtuhc.model.Project;
@Mapper
public interface ProjectMapper {
	Long createProject(Project project);
	Project getProjectById(Long tId);
	Integer updateProject(Project project);
	Integer deleteProjectById(Long tId);
}
