package edu.swjtuhc.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import edu.swjtuhc.model.Project;
import edu.swjtuhc.model.UserProfile;
import edu.swjtuhc.service.ProjectService;
import edu.swjtuhc.service.UserService;
import edu.swjtuhc.utils.JwtTokenUtil;
import net.sf.json.JSONObject;

@RestController
@RequestMapping("/project")
public class ProjectController {
	@Value("${jwt.header}")
	private String tokenHeader;

	@Value("${jwt.tokenHead}")
	private String tokenHead;

	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private UserService userService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();

	@PreAuthorize("hasRole('ROLE_TEACHER')")
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String createProject(HttpServletRequest request) {
		String token = request.getHeader(tokenHeader).substring(tokenHead.length());
		String account = jwtTokenUtil.getUsernameFromToken(token);
		JSONObject result = new JSONObject();
		if (token != null && account != null) {
			
			Long pId = -1L;
			try {
				pId = projectService.createProject(account);
				if(pId!=0) {
					result.put("state", "success");
					result.put("pId", pId);
				}else {
					result.put("state", "fail");
					result.put("msg", "课题项目状态出错");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				result.put("state", "fail");
				result.put("msg", "课题项目状态出错");
			}
			
		} else {			
			result.put("state","fail");
			result.put("msg","用户错误");
		}
		return result.toString();
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String upload(HttpServletRequest request, @RequestBody Project project) {
		String token = request.getHeader(tokenHeader).substring(tokenHead.length());
		String account = project.getUploader();
		JSONObject result = new JSONObject();
		UserProfile user = userService.getUserProfileByAccount(account);
		if (token != null && account != null&&user!=null) {
			// System.err.println(jwtTokenUtil.getUsernameFromToken(token));
			if (jwtTokenUtil.getUsernameFromToken(token).equals(account)) {

				Integer state = null;
				try {
					state = projectService.updateProject(project,user);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(state==1) {
					result.put("state", "success");
					result.put("msg", "更新成功");
				}else {
					result.put("state", "fail");
					result.put("msg", "更新失败");
				}
			} else {
				result.put("state", "fail");
				result.put("msg", "用户权限错误");
			}
			
		} else {
			result.put("state", "fail");
			result.put("msg", "请登陆");
		}
		return result.toString();
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String deleteProject(HttpServletRequest request, @RequestBody Project project) {
		String token = request.getHeader(tokenHeader).substring(tokenHead.length());
		Project t = projectService.getProjectById(project.getpId());
		String account = jwtTokenUtil.getUsernameFromToken(token);
		JSONObject result = new JSONObject();
		if(t!=null&&t.getUploader().equals(account)) {
			Integer i=0;
			try {
				i = projectService.deleteProject(t.getpId());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (i==1) {
				result.put("state", "success");
				result.put("msg", "删除成功");
			}else {
				result.put("state", "fail");
				result.put("msg", "数据库访问错误，删除失败");
			}
		}else {
			result.put("state", "fail");
			result.put("msg", "课题项目ID或用户权限错误");
		}
		return result.toString();		
	}
	
	@RequestMapping(value="/getById",method=RequestMethod.POST)
	public String getById(HttpServletRequest request, @RequestBody Project project) {
		String token = request.getHeader(tokenHeader).substring(tokenHead.length());
		Project t = projectService.getProjectById(project.getpId());
		String account = jwtTokenUtil.getUsernameFromToken(token);
		JSONObject result = new JSONObject();
		if(t==null) {
			result.put("state", "fail");
			result.put("msg", "课题项目成果不存在");
		}
		else if(t.getUploader().equals(account)) {
			result.put("state", "success");
			result.put("project", JSONObject.fromObject(t).toString());
		}else {
			result.put("state", "fail");
			result.put("msg", "用户权限错误");
		}
		
		return result.toString();
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN_01','ROLE_ADMIN_02')")
	@RequestMapping(value="/adminGetById",method=RequestMethod.POST)
	public String adminGetById(HttpServletRequest request, @RequestBody Project project) {
		Project t = projectService.getProjectById(project.getpId());
		JSONObject result = new JSONObject();
		if(t!=null) {
			result.put("state", "success");
			result.put("project", JSONObject.fromObject(t).toString());
		}else {
			result.put("state", "fail");
			result.put("msg", "课题项目成果不存在");
		}
		
		return result.toString();
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN_01')")
	@RequestMapping(value = "/adminModify", method = RequestMethod.POST)
	public String modifyProject(HttpServletRequest request, @RequestBody Project project) {
		
		
		JSONObject result = new JSONObject();
		Integer i=0;
		try {
			i = projectService.modifyProject(project);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(i==1) {
			result.put("state", "success");
			result.put("msg", "修改成功");
		}else {
			result.put("state", "fail");
			result.put("msg", "修改失败");
		}
		
		return result.toString();
	}
}
