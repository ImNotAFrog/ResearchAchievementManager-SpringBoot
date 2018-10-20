package edu.swjtuhc.serviceImpl;

import java.io.IOException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import edu.swjtuhc.mapper.AchievementMapper;
import edu.swjtuhc.mapper.ProjectMapper;
import edu.swjtuhc.model.Achievement;
import edu.swjtuhc.model.Project;
import edu.swjtuhc.model.Project;
import edu.swjtuhc.model.UserProfile;
import edu.swjtuhc.service.ProjectService;
import edu.swjtuhc.utils.IdWorker;
import edu.swjtuhc.utils.ModelUtil;

@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	ProjectMapper projectMapper;
	
	@Autowired
	AchievementMapper achievementMapper;
	
	@Override
	public Long createProject(String account) throws IOException {
		// TODO Auto-generated method stub
		Project project = new Project();
		project.setUploader(account);
		project.setUploadDate(new Date());
		project.setpId(IdWorker.getInstance().nextId());
		Achievement achievement=new Achievement();
		achievement.setaId(project.getpId());
		achievement.setUploader(account);
		achievement.setUploadDate(project.getUploadDate());
		achievement.setState(0);
		achievement.setType("project");
		achievement.setState(1);
		int i =createProject(project,achievement);
		if(i==1) {
			return project.getpId();
		}else {
			return 0L;
		}
		
	}

	@Override
	public Project getProjectById(Long pId)  {
		// TODO Auto-generated method stub
		return projectMapper.getProjectById(pId);
	}

	@Override
	public Integer updateProject(Project project,UserProfile user) throws IOException {
		// TODO Auto-generated method stub
		Project p = getProjectById(project.getpId());
		if(p==null) {
			return 0;
		}
		Achievement achievement= achievementMapper.getAchievementById(p.getpId());
		if(achievement==null||achievement.getState()>1) {
			return 0;
		}
		p = (Project) ModelUtil.updateModelByModel(p, project);
		p.setDepartment(user.getDepartment());
		p.setSubDepartment(user.getSubDepartment());
		p.setUploader(user.getAccount());
		p.setUploadDate(new Date());
		achievement.setDepartment(p.getDepartment());
		achievement.setSubDepartment(p.getSubDepartment());
		achievement.setUploadDate(p.getUploadDate());
		achievement.setName(p.getpName());
		int i = updateProject(p, achievement);
		
		return i;
	}

	@Override
	public Integer deleteProject(Long pId) throws IOException {
		// TODO Auto-generated method stub		
		int i = deleteProject(pId, pId);
		return i;
	}

	@Override
	public Integer modifyProject(Project project) throws IOException {
		// TODO Auto-generated method stub
		Project p = getProjectById(project.getpId());
		if(p==null) {
			return 0;
		}
		p = (Project) ModelUtil.updateModelByModel(p, project);
		p.setUploadDate(new Date());
		
		Achievement achievement = achievementMapper.getAchievementById(p.getpId());
		achievement.setName(p.getpName());
		achievement.setDepartment(p.getDepartment());
		achievement.setSubDepartment(p.getSubDepartment());
		achievement.setUploadDate(p.getUploadDate());
		achievement.setUploader(p.getUploader());
		int i = updateProject(p, achievement);
		return i;
	}
	
	@Override
	public Integer appendAttachment(Project project) {
		// TODO Auto-generated method stub
		return appendAttachment(project.getpId(), project.getAttachment());
	}

	@Override
	public Integer removeAttachment(Project project) {
		// TODO Auto-generated method stub
		return removeAttachment(project.getpId(), project.getAttachment());
	}

	@Transactional(isolation=Isolation.SERIALIZABLE)
	private int createProject(Project project, Achievement achievement){
		int i=0;
		if(projectMapper.createProject(project)==1) {
			if(achievementMapper.createAchievement(achievement)==1) {
				i=1;
			}
		}
		
		return i;		
	}
	@Transactional(isolation=Isolation.SERIALIZABLE)
	private int updateProject(Project project, Achievement achievement) {
		int i=0;
		if(projectMapper.updateProject(project)==1) {
			if(achievementMapper.updateAchievement(achievement)==1) {
				i=1;
			}
		}		
		return i;	
	}
	
	@Transactional(isolation=Isolation.SERIALIZABLE)
	private int deleteProject(Long pId, Long aId) {
		int i=0;
		if(projectMapper.deleteProjectById(pId)==1) {
			if(achievementMapper.deleteAchievement(aId)==1) {
				i=1;
			}
		}		
		return i;	
	}

	@Transactional(propagation=Propagation.REQUIRES_NEW,isolation=Isolation.SERIALIZABLE)
	private synchronized int appendAttachment(Long tId, String attachment) {
		Project p = projectMapper.getProjectById(tId);
		p.setAttachment(ModelUtil.appendPath(p.getAttachment(), attachment));
		int i=0;
		if(projectMapper.updateProject(p)==1) {
			i=1;
		}		
		return i;	
	}

	@Transactional(propagation=Propagation.REQUIRES_NEW,isolation=Isolation.SERIALIZABLE)
	private synchronized int removeAttachment(Long tId, String attachment) {
		Project p = projectMapper.getProjectById(tId);
		p.setAttachment(ModelUtil.deletePath(p.getAttachment(), attachment));
		int i=0;
		if(projectMapper.updateProject(p)==1) {
			i=1;
		}		
		return i;	
	}

}
