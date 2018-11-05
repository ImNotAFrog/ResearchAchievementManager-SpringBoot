package edu.swjtuhc.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import edu.swjtuhc.model.PagingRequestMsg;
import edu.swjtuhc.model.ResearchActivity;
import edu.swjtuhc.model.UserProfile;
import edu.swjtuhc.service.ResearchActivityService;
import edu.swjtuhc.service.UserService;
import edu.swjtuhc.utils.JwtTokenUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@RestController
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
			Long actId = -1L;
			actId = researchActivityService.createResearchActivity(account);
			if (actId != 0) {
				result.put("state", "success");
				result.put("actId", actId);
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
		String account = researchActivity.getApplicant();
		UserProfile user = userService.getUserProfileByAccount(account);
		if (token != null && account != null&&user!=null) {
			// System.err.println(jwtTokenUtil.getUsernameFromToken(token));
			if (jwtTokenUtil.getUsernameFromToken(token).equals(account)) {

				Integer i = null;
				i = researchActivityService.updateResearchActivity(researchActivity);
				if(i==1) {
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
	public String delete(HttpServletRequest request, @RequestBody ResearchActivity ra) {
		String token = request.getHeader(tokenHeader).substring(tokenHead.length());
		ResearchActivity researchActivity = researchActivityService.getResearchActivityById(ra.getActId());
		String account = jwtTokenUtil.getUsernameFromToken(token);
		JSONObject result = new JSONObject();
		if(researchActivity!=null) {
			if(researchActivity.getApplicant().equals(account)||request.isUserInRole("ROLE_ADMIN_01")) {
				Integer i=0;
				i = researchActivityService.deleteResearchActivity(researchActivity.getActId());
				if (i==1) {
					result.put("state", "success");
					result.put("msg", "删除成功");
				}else {
					result.put("state", "fail");
					result.put("msg", "数据库访问错误，删除失败");
				}
			}else {
				result.put("state", "fail");
				result.put("msg", "用户权限错误");
			}			
		}else {
			result.put("state", "fail");
			result.put("msg", "ID错误");
		}
		return result.toString();			
	}
	@RequestMapping(value = "/getListByAccount", method = RequestMethod.POST)
	public String getListByAccount(HttpServletRequest request, @RequestBody PagingRequestMsg msg) {
		String token = request.getHeader(tokenHeader).substring(tokenHead.length());
		String account = jwtTokenUtil.getUsernameFromToken(token);

		if (request.isUserInRole("ROLE_ADMIN_01") && msg.getAccount() != null) {
		} else {
			msg.setAccount(account);
		}
		JSONObject result = new JSONObject();
		try {
			List<ResearchActivity> list = researchActivityService.getResearchActivityByAccount(msg);
			JSONArray jList = null;
			if (list.size() > 0) {
				msg.setStart((msg.getPage() - 1) * msg.getLimit());
				Integer toIndex = ((msg.getStart() + msg.getLimit()) < list.size()) ? (msg.getStart() + msg.getLimit())
						: list.size();
				List<ResearchActivity> pageList = list.subList(msg.getStart(), toIndex);
				jList = JSONArray.fromObject(pageList);
			} else {
				jList = JSONArray.fromObject(list);
			}

			result.put("code", 0);
			result.put("msg", "请求成功");
			result.put("count", list.size());
			result.put("data", jList);
			return result.toString();
		} catch (Exception e) {
			result.put("state", -1);
			result.put("msg", e);
			e.printStackTrace();
			return result.toString();
		}
	}
	@RequestMapping(value="/getById",method=RequestMethod.POST)
	public String getById(HttpServletRequest request, @RequestBody Long actId) {
		String token = request.getHeader(tokenHeader).substring(tokenHead.length());
		ResearchActivity ra = researchActivityService.getResearchActivityById(actId);
		String account = jwtTokenUtil.getUsernameFromToken(token);
		JSONObject result = new JSONObject();
		if(ra==null) {
			result.put("state", "fail");
			result.put("msg", "法律法规成果不存在");
		}
		else if(ra.getApplicant().equals(account)||request.isUserInRole("ROLE_ADMIN_01")) {
			result.put("state", "success");
			result.put("laws", ra);
		}else {
			result.put("state", "fail");
			result.put("msg", "用户权限错误");
		}		
		return result.toString();
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN_01')")
	@RequestMapping(value="/adminGetById",method=RequestMethod.POST)
	public String adminGetById(HttpServletRequest request, @RequestBody Long actId) {
		ResearchActivity ra = researchActivityService.getResearchActivityById(actId);
		JSONObject result = new JSONObject();
		if(ra!=null) {
			result.put("state", "success");
			result.put("laws", ra);
		}else {
			result.put("state", "fail");
			result.put("msg", "法律法规成果不存在");
		}
		
		return result.toString();
	}
	@PreAuthorize("hasRole('ROLE_ADMIN_01')")
	@RequestMapping(value = "/adminModify", method = RequestMethod.POST)
	public String modifyLaws(HttpServletRequest request, @RequestBody ResearchActivity ra) {
		
		
		JSONObject result = new JSONObject();
		Integer i=0;
		i = researchActivityService.modifyResearchActivity(ra);
		if(i==1) {
			result.put("state", "success");
			result.put("msg", "修改成功");
		}else {
			result.put("state", "fail");
			result.put("msg", "修改失败");
		}
		
		return result.toString();
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN_01')")
	@RequestMapping(value = "/getAll", method = RequestMethod.POST)
	public String getAll(HttpServletRequest request, @RequestBody PagingRequestMsg msg) {
		JSONObject result = new JSONObject();
		try {
			List<ResearchActivity> list = researchActivityService.getResearchActivityList(msg);
			JSONArray jList = null;
			if (list.size() > 0) {
				msg.setStart((msg.getPage() - 1) * msg.getLimit());
				Integer toIndex = ((msg.getStart() + msg.getLimit()) < list.size()) ? (msg.getStart() + msg.getLimit())
						: list.size();
				List<ResearchActivity> pageList = list.subList(msg.getStart(), toIndex);
				jList = JSONArray.fromObject(pageList);
			} else {
				jList = JSONArray.fromObject(list);
			}
			result.put("code", 0);
			result.put("msg", "请求成功");
			result.put("count", list.size());
			result.put("data", jList);
			return result.toString();
		} catch (Exception e) {
			result.put("state", -1);
			result.put("msg", e);
			e.printStackTrace();
			return result.toString();
		}
	}
	
	@PreAuthorize("hasRole('ROLE_TEACHER')")
	@RequestMapping(value = "/submit", method = RequestMethod.POST)
	public String submit(HttpServletRequest request, @RequestBody Long actId) {
		JSONObject result = new JSONObject();
		Integer i = 0;
		i = researchActivityService.submitResearchActivity(actId);
		if (i == 1) {
			result.put("state", "success");
			result.put("msg", "提交成功");
		} else {
			result.put("state", "fail");
			result.put("msg", "提交失败，请检查成果状态");
		}
		return result.toString();
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN_01')")
	@RequestMapping(value = "/publish", method = RequestMethod.POST)
	public String publish(HttpServletRequest request, @RequestBody Long actId) {
		JSONObject result = new JSONObject();
		Integer i = 0;
		i = researchActivityService.publishResearchActivity(actId);
		if (i == 1) {
			result.put("state", "success");
			result.put("msg", "已发布");
		} else {
			result.put("state", "fail");
			result.put("msg", "通过失败，请检查成果状态");
		}
		return result.toString();
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN_01')")
	@RequestMapping(value = "/reject", method = RequestMethod.POST)
	public String reject(HttpServletRequest request, @RequestBody Long actId) {
		JSONObject result = new JSONObject();
		Integer i = 0;
		i=researchActivityService.rejectResearchActivity(actId);
		if (i == 1) {
			result.put("state", "success");
			result.put("msg", "已驳回");
		} else {
			result.put("state", "fail");
			result.put("msg", "驳回失败，请检查活动状态");
		}
		return result.toString();
	}
	
	@PreAuthorize("hasRole('ROLE_TEACHER')")
	@RequestMapping(value = "/submitedWithdraw", method = RequestMethod.POST)
	public String submitedWithdraw(HttpServletRequest request, @RequestBody Long actId) {
		JSONObject result = new JSONObject();
		Integer i = 0;
		i = researchActivityService.withdrawResearchActivity(actId);
		if (i == 1) {
			result.put("state", "success");
			result.put("msg", "活动已撤回");
		} else {
			result.put("state", "fail");
			result.put("msg", "撤回失败，请检查成果状态");
		}
		return result.toString();
	}
	
}
