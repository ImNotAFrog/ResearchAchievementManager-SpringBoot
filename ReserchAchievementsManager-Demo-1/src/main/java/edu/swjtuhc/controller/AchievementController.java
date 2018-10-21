package edu.swjtuhc.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.swjtuhc.model.Achievement;
import edu.swjtuhc.model.Thesis;
import edu.swjtuhc.model.UserProfile;
import edu.swjtuhc.service.AchievementService;
import edu.swjtuhc.service.UserService;
import edu.swjtuhc.utils.JwtTokenUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@RestController
@RequestMapping("/achievement")
public class AchievementController {
	@Autowired
	AchievementService achievementService;
	
	@Value("${jwt.header}")
	private String tokenHeader;

	@Value("${jwt.tokenHead}")
	private String tokenHead;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
	
	@RequestMapping(value="/getListByAccount", method = RequestMethod.GET)
	public String getListByAccount(HttpServletRequest request){
		String token = request.getHeader(tokenHeader).substring(tokenHead.length());
		String account = jwtTokenUtil.getUsernameFromToken(token);
		JSONObject result = new JSONObject();
		
		try {
			List<Achievement> list = achievementService.getAchievementListByAccount(account);
			JSONArray jList = JSONArray.fromObject(list);
			result.put("state", "success");
			result.put("achievement", jList);
			return result.toString();
		} catch (Exception e) {
			result.put("state", "fail");
			result.put("msg", e);
			return result.toString();
		}
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN_01')")
	@RequestMapping(value="/getAll", method = RequestMethod.GET)
	public String getAll(HttpServletRequest request){
		JSONObject result = new JSONObject();		
		try {
			List<Achievement> list = achievementService.getAchievementList();
			JSONArray jList = JSONArray.fromObject(list);
			result.put("state", "success");
			result.put("achievement", jList);
			return result.toString();
		} catch (Exception e) {
			result.put("state", "fail");
			result.put("msg", e);
			e.printStackTrace();
			return result.toString();
		}
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN_02','ROLE_ADMIN_01')")
	@RequestMapping(value="/getListBySubDepartment", method = RequestMethod.GET)
	public String getListBySubDepartment(HttpServletRequest request){
		String token = request.getHeader(tokenHeader).substring(tokenHead.length());
		String account = jwtTokenUtil.getUsernameFromToken(token);
		JSONObject result = new JSONObject();
		UserProfile userProfile=userService.getUserProfileByAccount(account);
		String subDepartment=userProfile.getSubDepartment();
		if(subDepartment==null||subDepartment.length()==0) {
			result.put("state", "fail");
			result.put("msg", "用户未设置科室");
			return result.toString();
		}
		try {
			List<Achievement> list = achievementService.getAchievementListBySubDepartment(subDepartment);
			JSONArray jList = JSONArray.fromObject(list);
			result.put("state", "success");
			result.put("achievement", jList);
			return result.toString();
		} catch (Exception e) {
			result.put("state", "fail");
			result.put("msg", e);
			return result.toString();
		}
		
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN_02','ROLE_ADMIN_01')")
	@RequestMapping(value="/getListByName", method = RequestMethod.POST)
	public String getListByName(HttpServletRequest request, @RequestBody Map<String,Object> reqMap){
		String token = request.getHeader(tokenHeader).substring(tokenHead.length());
		JSONObject result = new JSONObject();
		String name=(String) reqMap.get("name");
		if(name==null){
			result.put("state", "fail");
			result.put("msg", "参数错误");
			return result.toString();
		}
		try {
			List<Achievement> list = achievementService.getAchievementByName(name);
			JSONArray jList = JSONArray.fromObject(list);
			result.put("state", "success");
			result.put("achievement", jList);
			return result.toString();
		} catch (Exception e) {
			result.put("state", "fail");
			result.put("msg", e);
			return result.toString();
		}
		
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN_02')")
	@RequestMapping(value = "/preCheck", method = RequestMethod.POST)
	public String preCheck(HttpServletRequest request, @RequestBody Map<String,Object> reqMap) {		
		JSONObject result = new JSONObject();
		Long aId=-1L;
		try {
			aId = (Long) reqMap.get("aId");
		} catch (Exception e) {
			result.put("state", "fail");
			result.put("msg", "成果Id格式错误");
			return result.toString();
		}
		Integer i=0;
		i = achievementService.precheck(aId);
		if(i==1) {
			result.put("state", "success");
			result.put("msg", "初审成功");
		}else {
			result.put("state", "fail");
			result.put("msg", "初审失败，请检查成果状态");
		}
		
		return result.toString();
	}
	@PreAuthorize("hasRole('ROLE_TEACHER')")
	@RequestMapping(value = "/submit", method = RequestMethod.POST)
	public String submit(HttpServletRequest request, @RequestBody Map<String,Object> reqMap) {		
		JSONObject result = new JSONObject();
		Long aId=-1L;
		try {
			aId = (Long) reqMap.get("aId");
		} catch (Exception e) {
			result.put("state", "fail");
			result.put("msg", "成果Id格式错误");
			return result.toString();
		}
		Integer i=0;
		i = achievementService.submit(aId);
		if(i==1) {
			result.put("state", "success");
			result.put("msg", "提交成功");
		}else {
			result.put("state", "fail");
			result.put("msg", "提交失败，请检查成果状态");
		}		
		return result.toString();
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN_01')")
	@RequestMapping(value = "/approve", method = RequestMethod.POST)
	public String approve(HttpServletRequest request, @RequestBody Map<String,Object> reqMap) {		
		JSONObject result = new JSONObject();
		Long aId=-1L;
		try {
			aId = (Long) reqMap.get("aId");
		} catch (Exception e) {
			result.put("state", "fail");
			result.put("msg", "成果Id格式错误");
			return result.toString();
		}
		Integer i=0;
		i = achievementService.approve(aId);
		if(i==1) {
			result.put("state", "success");
			result.put("msg", "已通过");
		}else {
			result.put("state", "fail");
			result.put("msg", "通过失败，请检查成果状态");
		}		
		return result.toString();
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN_01','ROLE_ADMIN_02')")
	@RequestMapping(value = "/reject", method = RequestMethod.POST)
	public String reject(HttpServletRequest request, @RequestBody Map<String,Object> reqMap) {		
		JSONObject result = new JSONObject();
		Long aId=-1L;
		try {
			aId = (Long) reqMap.get("aId");
		} catch (Exception e) {
			result.put("state", "fail");
			result.put("msg", "成果Id格式错误");
			return result.toString();
		}
		Integer i=0;
		i = achievementService.reject(aId);
		if(i==1) {
			result.put("state", "success");
			result.put("msg", "已驳回");
		}else {
			result.put("state", "fail");
			result.put("msg", "驳回失败，请检查成果状态");
		}		
		return result.toString();
	}
	@PreAuthorize("hasRole('ROLE_ADMIN_01')")
	@RequestMapping(value = "/approvedWithdraw", method = RequestMethod.POST)
	public String approvedWithdraw(HttpServletRequest request, @RequestBody Map<String,Object> reqMap) {		
		JSONObject result = new JSONObject();
		Long aId=-1L;
		try {
			aId = (Long) reqMap.get("aId");
		} catch (Exception e) {
			result.put("state", "fail");
			result.put("msg", "成果Id格式错误");
			return result.toString();
		}
		Integer i=0;
		i = achievementService.approvedWithdraw(aId);
		if(i==1) {
			result.put("state", "success");
			result.put("msg", "成果已撤回");
		}else {
			result.put("state", "fail");
			result.put("msg", "撤回失败，请检查成果状态");
		}		
		return result.toString();
	}
	@PreAuthorize("hasRole('ROLE_ADMIN_02')")
	@RequestMapping(value = "/preCheckedWithdraw", method = RequestMethod.POST)
	public String preCheckedWithdraw(HttpServletRequest request, @RequestBody Map<String,Object> reqMap) {		
		JSONObject result = new JSONObject();
		Long aId=-1L;
		try {
			aId = (Long) reqMap.get("aId");
		} catch (Exception e) {
			result.put("state", "fail");
			result.put("msg", "成果Id格式错误");
			return result.toString();
		}
		Integer i=0;
		i = achievementService.preCheckedWithdraw(aId);
		if(i==1) {
			result.put("state", "success");
			result.put("msg", "成果已撤回");
		}else {
			result.put("state", "fail");
			result.put("msg", "撤回失败，请检查成果状态");
		}		
		return result.toString();
	}
	
	@PreAuthorize("hasRole('ROLE_TEACHER')")
	@RequestMapping(value = "/submitedWithdraw", method = RequestMethod.POST)
	public String submitedWithdraw(HttpServletRequest request, @RequestBody Map<String,Object> reqMap) {		
		JSONObject result = new JSONObject();
		Long aId=-1L;
		try {
			aId = (Long) reqMap.get("aId");
		} catch (Exception e) {
			result.put("state", "fail");
			result.put("msg", "成果Id格式错误");
			return result.toString();
		}
		Integer i=0;
		i = achievementService.submitedWithdraw(aId);
		if(i==1) {
			result.put("state", "success");
			result.put("msg", "成果已撤回");
		}else {
			result.put("state", "fail");
			result.put("msg", "撤回失败，请检查成果状态");
		}		
		return result.toString();
	}
}
