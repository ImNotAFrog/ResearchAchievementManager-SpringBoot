package edu.swjtuhc.controller;

import org.springframework.beans.factory.annotation.Value;
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
	
	@RequestMapping(value="/admin1.do",method=RequestMethod.GET)
	public String admin1() {
		return "admin1";
	}
	
	@RequestMapping(value="/admin2.do",method=RequestMethod.GET)
	public String admin2() {
		return "admin2";
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
	
	@RequestMapping(value="/patent/upload.do",method=RequestMethod.GET)
	public String patentUpload() {
		return "/patent/upload";
	}
	
	@RequestMapping(value="/textbook/upload.do",method=RequestMethod.GET)
	public String textbookUpload() {
		return "/textbook/upload";
	}
	
	@RequestMapping(value="/project/upload.do",method=RequestMethod.GET)
	public String projectUpload() {
		return "/project/upload";
	}
	
	@RequestMapping(value="/reformProject/upload.do",method=RequestMethod.GET)
	public String reformProjectUpload() {
		return "/reformProject/upload";
	}
	
	@RequestMapping(value="/laws/upload.do",method=RequestMethod.GET)
	public String lawUpload() {
		return "/laws/upload";
	}
	
	@RequestMapping(value="/thesis/edit.do",method=RequestMethod.GET)
	public String thesisEdit() {
		return "/thesis/edit";
	}
	
	@RequestMapping(value="/patent/edit.do",method=RequestMethod.GET)
	public String patentEdit() {
		return "/patent/edit";
	}
	
	@RequestMapping(value="/textbook/edit.do",method=RequestMethod.GET)
	public String textbookEdit() {
		return "/textbook/edit";
	}
	
	@RequestMapping(value="/project/edit.do",method=RequestMethod.GET)
	public String projectEdit() {
		return "/project/edit";
	}
	
	@RequestMapping(value="/reformProject/edit.do",method=RequestMethod.GET)
	public String reformProjectEdit() {
		return "/reformProject/edit";
	}
	
	@RequestMapping(value="/laws/edit.do",method=RequestMethod.GET)
	public String lawEdit() {
		return "/laws/edit";
	}
	
	@RequestMapping(value="/thesis/exam.do",method=RequestMethod.GET)
	public String thesisExam() {
		return "/thesis/exam";
	}
	
	@RequestMapping(value="/patent/exam.do",method=RequestMethod.GET)
	public String patentExam() {
		return "/patent/exam";
	}
	
	@RequestMapping(value="/textbook/exam.do",method=RequestMethod.GET)
	public String textbookExam() {
		return "/textbook/exam";
	}
	
	@RequestMapping(value="/project/exam.do",method=RequestMethod.GET)
	public String projectExam() {
		return "/project/exam";
	}
	
	@RequestMapping(value="/reformProject/exam.do",method=RequestMethod.GET)
	public String reformProjectExam() {
		return "/reformProject/exam";
	}
	
	@RequestMapping(value="/laws/exam.do",method=RequestMethod.GET)
	public String lawExam() {
		return "/laws/exam";
	}
	
	@RequestMapping(value="/news/detail.do",method=RequestMethod.GET)
	public String newsDetail() {
		return "/news/detail";
	}
	
	@RequestMapping(value="/news/edit.do",method=RequestMethod.GET)
	public String newsEdit() {
		return "/news/edit";
	}
	
	@RequestMapping(value="/news/list.do",method=RequestMethod.GET)
	public String newsList() {
		return "/news/list";
	}
	
	@RequestMapping(value="/ranking/individual.do",method=RequestMethod.GET)
	public String individual() {
		return "/ranking/individual.html";
	}
	@RequestMapping(value="/ranking/department.do",method=RequestMethod.GET)
	public String department() {
		return "/ranking/department.html";
	}
	
	@RequestMapping(value="/ranking/individualDetail.do",method=RequestMethod.GET)
	public String individualDetail() {
		return "/ranking/individualDetail.html";
	}
	@RequestMapping(value="/ranking/departmentDetail.do",method=RequestMethod.GET)
	public String departmentDetail() {
		return "/ranking/departmentDetail.html";
	}
	
	@RequestMapping(value="/achievementExport.do",method=RequestMethod.GET)
	public String achievementExport() {
		return "/achievementExport.html";
	}
	
	@RequestMapping(value="/user/management.do",method=RequestMethod.GET)
	public String userManagement() {
		return "/user/management.html";
	}
	
	@RequestMapping(value="/user/edit.do",method=RequestMethod.GET)
	public String userEdit() {
		return "/user/edit.html";
	}
	
	@RequestMapping(value="/researchActivity/upload.do",method=RequestMethod.GET)
	public String researchActivityUpload() {
		return "/researchActivity/upload.html";
	}
	@RequestMapping(value="/researchActivity/edit.do",method=RequestMethod.GET)
	public String researchActivityEdit() {
		return "/researchActivity/edit.html";
	}
	@RequestMapping(value="/researchActivity/publish.do",method=RequestMethod.GET)
	public String researchActivityPublish() {
		return "/researchActivity/publish.html";
	}
	@RequestMapping(value="/researchActivity/export.do",method=RequestMethod.GET)
	public String researchActivityExport() {
		return "/researchActivity/export.html";
	}
	@RequestMapping(value="/otherActivity/upload.do",method=RequestMethod.GET)
	public String otherActivityUpload() {
		return "/otherActivity/upload.html";
	}
	@RequestMapping(value="/otherActivity/edit.do",method=RequestMethod.GET)
	public String otherActivityEdit() {
		return "/otherActivity/edit.html";
	}
	@RequestMapping(value="/otherActivity/publish.do",method=RequestMethod.GET)
	public String otherActivityPublish() {
		return "/otherActivity/publish.html";
	}
	@RequestMapping(value="/otherActivity/export.do",method=RequestMethod.GET)
	public String otherActivityExport() {
		return "/otherActivity/export.html";
	}
	
	
}
