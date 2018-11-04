package edu.swjtuhc.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.swjtuhc.model.ReformProject;
import edu.swjtuhc.model.ResearchActivity;
import edu.swjtuhc.model.UserProfile;
import edu.swjtuhc.service.ResearchActivityService;
import edu.swjtuhc.service.UserService;
import edu.swjtuhc.utils.JwtTokenUtil;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("researchActivity")
public class ResearchActivityController {
	@Value("${jwt.header}")
	private String tokenHeader;

	@Value("${jwt.tokenHead}")
	private String tokenHead;

	@Autowired
	private JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();

	@Autowired
	private ResearchActivityService researchActivityService;
	
	@Autowired
	private UserService userService;

	@RequestMapping(value="/create", method = RequestMethod.GET)
	public String create(HttpServletRequest request) {
		JSONObject result = new JSONObject();
		String token = request.getHeader(tokenHeader).substring(tokenHead.length());
		String account = jwtTokenUtil.getUsernameFromToken(token);
		if (token != null && account != null) {
			Long raId = -1L;
			raId = researchActivityService.createResearchActivity(account);
			if (raId != 0) {
				result.put("state", "success");
				result.put("rpId", raId);
			} else {
				result.put("state", "fail");
				result.put("msg", "状态出错");
			}

		} else {
			result.put("state", "fail");
			result.put("msg", "用户错误");
		}
		return result.toString();
	}
	
	@RequestMapping(value="/upload", method = RequestMethod.POST)
	public String upload(HttpServletRequest request, @RequestBody ResearchActivity researchActivity) {
		JSONObject result = new JSONObject();
		String token = request.getHeader(tokenHeader).substring(tokenHead.length());
		String account = jwtTokenUtil.getUsernameFromToken(token);
		UserProfile user = userService.getUserProfileByAccount(account);
		if (token != null && account != null&&user!=null) {
			// System.err.println(jwtTokenUtil.getUsernameFromToken(token));
			if (jwtTokenUtil.getUsernameFromToken(token).equals(account)) {

				Integer state = null;
				state = researchActivityService.updateResearchActivity(researchActivity);
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
	
}
