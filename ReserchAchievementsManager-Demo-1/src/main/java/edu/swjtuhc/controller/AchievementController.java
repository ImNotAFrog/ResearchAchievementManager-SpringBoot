package edu.swjtuhc.controller;

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
		List<Achievement> list = achievementService.getAchievementListByAccount(account);
		JSONArray jList = JSONArray.fromObject(list);
		result.put("state", "success");
		result.put("achievement", jList);
		return result.toString();
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN_01')")
	@RequestMapping(value="/getAll", method = RequestMethod.GET)
	public String getAll(HttpServletRequest request){
		JSONObject result = new JSONObject();
		List<Achievement> list = achievementService.getAchievementList();
		JSONArray jList = JSONArray.fromObject(list);
		result.put("state", "success");
		result.put("achievement", jList);
		return result.toString();
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN_02')")
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
		List<Achievement> list = achievementService.getAchievementListBySubDepartment(subDepartment);
		JSONArray jList = JSONArray.fromObject(list);
		result.put("state", "success");
		result.put("achievement", jList);
		return result.toString();
	}
}
