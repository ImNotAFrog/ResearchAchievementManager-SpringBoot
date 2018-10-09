package edu.swjtuhc.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.swjtuhc.model.SysUser;
import edu.swjtuhc.model.Thesis;
import edu.swjtuhc.service.ThesisService;
import edu.swjtuhc.utils.JwtTokenUtil;

@RestController
@RequestMapping("/thesis")
public class ThesisController {
	@Value("${jwt.header}")
	private String tokenHeader;

	@Value("${jwt.tokenHead}")
	private String tokenHead;
	
	@Autowired 
	private ThesisService thesisService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();

	@PreAuthorize("hasAnyRole('ROLE_TEACHER')")
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public ResponseEntity<?> getUserProfileByAccount(HttpServletRequest request, @RequestBody Thesis thesis) {
		String token = request.getHeader(tokenHeader).substring(tokenHead.length());
		String account = thesis.getUploader();
		if (token != null && account != null) {
			// System.err.println(jwtTokenUtil.getUsernameFromToken(token));
			if (jwtTokenUtil.getUsernameFromToken(token).equals(account)) {
				
				return ResponseEntity.ok(thesisService.createThesis(thesis));
			} else {
				return ResponseEntity.badRequest().body(null);
			}
		} else {
			return ResponseEntity.badRequest().body(null);
		}

	}
}
