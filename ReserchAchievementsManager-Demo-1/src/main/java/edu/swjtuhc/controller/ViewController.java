package edu.swjtuhc.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ViewController {
    @Value("${jwt.header}")
    private String tokenHeader;
    
    
	@RequestMapping("/index")
	public String index() {
		return "index";
	}
	
	@RequestMapping("/header.html")
	public String header() {
		return "header";
	}
	
	@RequestMapping("/auth")
	public String auth() {
		return "login";
	}
	
	@RequestMapping(value="/teacher.do",method=RequestMethod.GET)
	public String teacher() {
		return "teacher";
	}
	
	@RequestMapping(value="/editUser.do",method=RequestMethod.GET)
	public String editUser() {
		return "editUser";
	}
	
	@RequestMapping(value="/editPassword.do",method=RequestMethod.GET)
	public String editPassword() {
		return "editPassword";
	}
	@RequestMapping(value="/thesis/upload.do",method=RequestMethod.GET)
	public String thesisUpload() {
		return "/thesis/upload";
	}
}
