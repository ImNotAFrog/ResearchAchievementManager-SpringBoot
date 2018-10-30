package edu.swjtuhc.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.swjtuhc.model.IndividualRank;
import edu.swjtuhc.model.RankingRequestMsg;
import edu.swjtuhc.service.RankingService;
import net.sf.json.JSONObject;

@RestController
@RequestMapping("/ranking")
public class RankingController {
	
	@Autowired
	RankingService rankingService;
	
	@PreAuthorize("hasRole('ROLE_ADMIN_01')")
	@RequestMapping(value = "/individual", method = RequestMethod.POST)
	public String individual(HttpServletRequest request, @RequestBody RankingRequestMsg msg) {
		JSONObject result = new JSONObject();
		try {
			List<IndividualRank> list = rankingService.individual(msg);
			result.put("state", "success");
			result.put("data", list);
		} catch (Exception e) {
			// TODO: handle exception
			result = new JSONObject();
			result.put("state", "fail");
			result.put("msg", e);
			e.printStackTrace();
		}		
		return result.toString();
	}
}
