package edu.swjtuhc.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.swjtuhc.enums.Department;
import edu.swjtuhc.mapper.UserMapper;
import edu.swjtuhc.mapper.UserProfileMapper;
import edu.swjtuhc.model.SysUser;
import edu.swjtuhc.model.UserProfile;
import edu.swjtuhc.service.AuthService;
import edu.swjtuhc.service.UserService;
import edu.swjtuhc.utils.DepartmentListUtil;
import edu.swjtuhc.utils.JwtTokenUtil;
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
    public List<SysUser> getUserList() {
        return userService.getUserList();
    }
    
    @PostAuthorize("hasRole('ROLE_TEACHER') AND returnObject.account == principal.username")
    @RequestMapping(value="/getUserProfileByAccount",method = RequestMethod.POST)
    public UserProfile getUserProfileByAccount(HttpServletRequest request, @RequestBody SysUser user) {
    	String account = user.getAccount();
        return userService.getUserProfileByAccount(account);}
    
    @PreAuthorize("hasAnyRole('ROLE_ADMIN_01','ROLE_ADMIN_02','ROLE_TEACHER')")
    @RequestMapping(value="/getDepartmentList",method = RequestMethod.GET)
    public ResponseEntity<?> getDepartmentList(HttpServletRequest request) {
    	return ResponseEntity.ok(userService.getDepartmentList());    	
    }
    
    @PreAuthorize("hasAnyRole('ROLE_ADMIN_01','ROLE_ADMIN_02','ROLE_TEACHER')")
    @RequestMapping(value="/updateUserProfile",method = RequestMethod.POST)
    public ResponseEntity<?> updateUserProfile(@RequestBody UserProfile userProfile) {
    	
    	return ResponseEntity.ok(userService.updateUserProfile(userProfile));    	
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN_01','ROLE_ADMIN_02','ROLE_TEACHER')")
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
}
