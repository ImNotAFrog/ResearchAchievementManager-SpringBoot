package edu.swjtuhc.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.swjtuhc.model.RankingRequestMsg;
import net.sf.json.JSONObject;

@RestController
@RequestMapping("/ranking")
public class RankingController {
	
	
	@PreAuthorize("hasRole('ROLE_ADMIN_01')")
	@RequestMapping(value = "/individual", method = RequestMethod.POST)
	public String individual(HttpServletRequest request, @RequestBody RankingRequestMsg msg) {
		JSONObject result = new JSONObject();
		
		
		
		return result.toString();
	}
}
