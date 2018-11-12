package edu.swjtuhc.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.swjtuhc.mapper.UserMapper;
import edu.swjtuhc.model.SysUser;
import edu.swjtuhc.model.UserInfo;
import edu.swjtuhc.model.UserProfile;
import edu.swjtuhc.service.UserService;
import edu.swjtuhc.utils.JwtTokenUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@RestController
@RequestMapping("/user")
public class UserController {
    @Value("${jwt.header}")
    private String tokenHeader;
    
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    private UserService userService;
    
    @Autowired
    private JwtTokenUtil jwtTokenUtil=new JwtTokenUtil();
    
    @PreAuthorize("hasRole('ROLE_ADMIN_01')")
    @RequestMapping(value="/getUserList",method = RequestMethod.GET)
    public String getUserList() {

    	JSONObject result = new JSONObject();
    	try {
    		List<UserInfo> list =userService.getUserList();
        	result.put("state", "success");
        	result.put("users", list);
            return result.toString();
		} catch (Exception e) {
			// TODO: handle exception
			result.put("state", "fail");
        	result.put("msg", e);
            return result.toString();
		}
    	
    }
    @PreAuthorize("hasRole('ROLE_ADMIN_01')")
    @RequestMapping(value="/getUserById",method = RequestMethod.POST)
    public String getUserById(@RequestBody UserInfo userInfo) {
    	JSONObject result = new JSONObject();
    	try {
        	UserInfo u = userService.getUserById(userInfo.getuId());
        	result.put("state", "success");
        	result.put("user", u);
            return result.toString();
		} catch (Exception e) {
			// TODO: handle exception
			result.put("state", "fail");
        	result.put("msg", e);
            return result.toString();
		}
    	
    }
    
    @RequestMapping(value="/getUserProfileByAccount",method = RequestMethod.GET)
    public String getUserProfileByAccount(HttpServletRequest request) {
    	String token = request.getHeader(tokenHeader).substring(tokenHead.length());
		String account = jwtTokenUtil.getUsernameFromToken(token);
    	JSONObject result= new JSONObject();    	   	
        try {
        	UserProfile p = userService.getUserProfileByAccount(account);
        	if(p==null) {
        		result.put("state", "fail");
            	result.put("msg", "用户个人信息不存在，请联系管理员"); 
                return result.toString();
        	}
        	result.put("state", "success");
        	result.put("profile", p); 
            return result.toString();
		} catch (Exception e) {
			result.put("state", "fail");
        	result.put("msg", e);
        	e.printStackTrace();
            return result.toString();
		}
    }
    
   
    @RequestMapping(value="/getDepartmentList",method = RequestMethod.GET)
    public String getDepartmentList(HttpServletRequest request) {
    	JSONObject result= new JSONObject();   
    	try {
    		
    		JSONArray dep = userService.getDepartmentList();
    		result.put("state", "success");
    		result.put("dep", dep);
        	return result.toString(); 
		} catch (Exception e) {
			result.put("state", "fail");
        	result.put("msg", e);
            return result.toString();
		}   	
    }
    
    @RequestMapping(value="/updateUserProfile",method = RequestMethod.POST)
    public ResponseEntity<?> updateUserProfile(@RequestBody UserProfile userProfile) {
    	
    	return ResponseEntity.ok(userService.updateUserProfile(userProfile));    	
    }

    @RequestMapping(value="/changePassword",method = RequestMethod.POST)
    public ResponseEntity<?> changePassword(HttpServletRequest request,@RequestBody JSONObject param) {
    	String token = request.getHeader(tokenHeader).substring(tokenHead.length());
    	String account = param.getString("account");
    	String oldPassword= param.getString("oldPassword");
    	String newPassword = param.getString("newPassword");
    	if(token!=null&&account!=null) {
    		//System.err.println(jwtTokenUtil.getUsernameFromToken(token));
    		if(jwtTokenUtil.getUsernameFromToken(token).equals(account)) {
        		return ResponseEntity.ok(userService.changePassword(account,oldPassword,newPassword));
        	}else {
        		return ResponseEntity.badRequest().body(null);
        	}
    	}else {
    		return ResponseEntity.badRequest().body(null);
    	} 
    }
    
    @PreAuthorize("hasRole('ROLE_ADMIN_01')") 
    @RequestMapping(value="/createUser",method = RequestMethod.POST)
    public String createUser(HttpServletRequest request,@RequestBody UserInfo userInfo) {
    	Integer i = userService.createUser(userInfo);
    	JSONObject result = new JSONObject();
    	if(i==1) {
    		result.put("state", "success");
    	}else {
    		result.put("state", "fail");
    		result.put("msg", "请检查账户名是否唯一");
    	}
    	return result.toString();
    }
    
    @PreAuthorize("hasRole('ROLE_ADMIN_01')") 
    @RequestMapping(value="/deleteUser",method = RequestMethod.POST)
    public String deleteUser(HttpServletRequest request,@RequestBody UserInfo userInfo) {
    	Integer i = userService.deleteUser(userInfo.getuId());
    	JSONObject result = new JSONObject();
    	if(i==1) {
    		result.put("state", "success");
    	}else {
    		result.put("state", "fail");
    		result.put("msg", "请检查Id是否正确");
    	}
    	return result.toString();
    }
    
    @PreAuthorize("hasRole('ROLE_ADMIN_01')") 
    @RequestMapping(value="/updateUser",method = RequestMethod.POST)
    public String updateUser(HttpServletRequest request,@RequestBody UserInfo userInfo) {
    	Integer i = userService.updateUser(userInfo);
    	JSONObject result = new JSONObject();
    	if(i==1) {
    		result.put("state", "success");
    	}else {
    		result.put("state", "fail");
    		result.put("msg", "参数错误");
    	}
    	return result.toString();
    }
    
}
