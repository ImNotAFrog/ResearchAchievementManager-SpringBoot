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

import edu.swjtuhc.model.Patent;
import edu.swjtuhc.model.UserProfile;
import edu.swjtuhc.service.PatentService;
import edu.swjtuhc.service.UserService;
import edu.swjtuhc.utils.JwtTokenUtil;
import net.sf.json.JSONObject;

@RestController
@RequestMapping("/patent")
public class PatentController {
	@Value("${jwt.header}")
	private String tokenHeader;

	@Value("${jwt.tokenHead}")
	private String tokenHead;

	@Autowired
	private PatentService patentService;
	
	@Autowired
	private UserService userService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();

	@PreAuthorize("hasRole('ROLE_TEACHER')")
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String createPatent(HttpServletRequest request) {
		String token = request.getHeader(tokenHeader).substring(tokenHead.length());
		String account = jwtTokenUtil.getUsernameFromToken(token);
		JSONObject result = new JSONObject();
		if (token != null && account != null) {
			
			Long paId = -1L;
			try {
				paId = patentService.createPatent(account);
				if(paId!=0) {
					result.put("state", "success");
					result.put("paId", paId);
				}else {
					result.put("state", "fail");
					result.put("msg", "专利状态出错");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				result.put("state", "fail");
				result.put("msg", "专利状态出错");
			}
			
		} else {			
			result.put("state","fail");
			result.put("msg","用户错误");
		}
		return result.toString();
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String upload(HttpServletRequest request, @RequestBody Patent patent) {
		String token = request.getHeader(tokenHeader).substring(tokenHead.length());
		String account = patent.getUploader();
		JSONObject result = new JSONObject();
		UserProfile user = userService.getUserProfileByAccount(account);
		if (token != null && account != null&&user!=null) {
			// System.err.println(jwtTokenUtil.getUsernameFromToken(token));
			if (jwtTokenUtil.getUsernameFromToken(token).equals(account)) {

				Integer state = null;
				try {
					state = patentService.updatePatent(patent,user);
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
	public String deletePatent(HttpServletRequest request, @RequestBody Patent patent) {
		String token = request.getHeader(tokenHeader).substring(tokenHead.length());
		Patent pa = patentService.getPatentById(patent.getPaId());
		String account = jwtTokenUtil.getUsernameFromToken(token);
		JSONObject result = new JSONObject();
		if(pa!=null&&pa.getUploader().equals(account)) {
			Integer i=0;
			try {
				i = patentService.deletePatent(pa.getPaId());
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
			result.put("msg", "专利ID或用户权限错误");
		}
		return result.toString();		
	}
	
	@RequestMapping(value="/getById",method=RequestMethod.POST)
	public String getById(HttpServletRequest request, @RequestBody Patent patent) {
		String token = request.getHeader(tokenHeader).substring(tokenHead.length());
		Patent pa = patentService.getPatentById(patent.getPaId());
		String account = jwtTokenUtil.getUsernameFromToken(token);
		JSONObject result = new JSONObject();
		if(pa==null) {
			result.put("state", "fail");
			result.put("msg", "专利成果不存在");
		}
		else if(pa.getUploader().equals(account)) {
			result.put("state", "success");
			result.put("patent", JSONObject.fromObject(pa).toString());
		}else {
			result.put("state", "fail");
			result.put("msg", "用户权限错误");
		}
		
		return result.toString();
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN_01','ROLE_ADMIN_02')")
	@RequestMapping(value="/adminGetById",method=RequestMethod.POST)
	public String adminGetById(HttpServletRequest request, @RequestBody Patent patent) {
		Patent pa = patentService.getPatentById(patent.getPaId());
		JSONObject result = new JSONObject();
		if(pa!=null) {
			result.put("state", "success");
			result.put("patent", JSONObject.fromObject(pa).toString());
		}else {
			result.put("state", "fail");
			result.put("msg", "专利成果不存在");
		}
		
		return result.toString();
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN_01')")
	@RequestMapping(value = "/adminModify", method = RequestMethod.POST)
	public String modifyPatent(HttpServletRequest request, @RequestBody Patent patent) {
		
		
		JSONObject result = new JSONObject();
		Integer i=0;
		try {
			i = patentService.modifyPatent(patent);
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
