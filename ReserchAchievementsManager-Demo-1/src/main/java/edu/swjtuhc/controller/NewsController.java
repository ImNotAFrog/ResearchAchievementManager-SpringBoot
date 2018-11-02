package edu.swjtuhc.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.swjtuhc.model.Achievement;
import edu.swjtuhc.model.News;
import edu.swjtuhc.model.Patent;
import edu.swjtuhc.model.PagingRequestMsg;
import edu.swjtuhc.service.NewsService;
import edu.swjtuhc.utils.JwtTokenUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@RestController
@RequestMapping("/news")
public class NewsController {
	@Value("${jwt.header}")
	private String tokenHeader;

	@Value("${jwt.tokenHead}")
	private String tokenHead;

	@Autowired
	NewsService newsService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();

	@PreAuthorize("hasRole('ROLE_ADMIN_01')")
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create(HttpServletRequest request) {
		String token = request.getHeader(tokenHeader).substring(tokenHead.length());
		String account = jwtTokenUtil.getUsernameFromToken(token);
		JSONObject result = new JSONObject();
		if (token != null && account != null) {

			Long nId = -1L;
			try {
				nId = newsService.createNews(account);
				if (nId != -1) {
					result.put("state", "success");
					result.put("nId", nId);
				} else {
					result.put("state", "fail");
					result.put("msg", "新闻状态出错");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				result.put("state", "fail");
				result.put("msg", "新闻状态出错");
			}

		} else {
			result.put("state", "fail");
			result.put("msg", "用户错误");
		}
		return result.toString();
	}

	@PreAuthorize("hasRole('ROLE_ADMIN_01')")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(HttpServletRequest request, @RequestBody News news) {
		String token = request.getHeader(tokenHeader).substring(tokenHead.length());
		String account = jwtTokenUtil.getUsernameFromToken(token);
		JSONObject result = new JSONObject();
		if (newsService.getStateById(news.getnId()) == 1) {
			news.setUploader(account);
			Integer i = newsService.updateNews(news);

			if (i == 1) {
				result.put("state", "success");
				result.put("msg", "更新成功");
			} else {
				result.put("state", "fail");
				result.put("msg", "更新失败,请检查信息");
			}
		} else {
			result.put("state", "fail");
			result.put("msg", "更新失败,请检查新闻状态");
		}
		return result.toString();
	}

	@PreAuthorize("hasRole('ROLE_ADMIN_01')")
	@RequestMapping(value = "/publish", method = RequestMethod.POST)
	public String publish(HttpServletRequest request, @RequestBody Map<String, String> reqMap) {

		String token = request.getHeader(tokenHeader).substring(tokenHead.length());
		String account = jwtTokenUtil.getUsernameFromToken(token);
		JSONObject result = new JSONObject();
		Long nId = -1L;
		try {
			System.out.println(reqMap.get("nId"));
			nId = Long.parseLong(reqMap.get("nId"));

		} catch (Exception e) {
			result.put("state", "fail");
			result.put("msg", "发布失败,请检查参数信息");
			return result.toString();
		}
		News news = newsService.getNewsById(nId);
		news.setUploader(account);
		if (news.getState() == 1) {
			news.setState(2);
			news.setUploader(account);
			Integer i = newsService.updateNews(news);
			if (i == 1) {
				result.put("state", "success");
				result.put("msg", "发布成功");
			} else {
				result.put("state", "fail");
				result.put("msg", "发布失败,请检查参数信息");
			}
		} else {
			result.put("state", "fail");
			result.put("msg", "发布失败,请检查新闻状态");
		}
		return result.toString();
	}

	@PreAuthorize("hasRole('ROLE_ADMIN_01')")
	@RequestMapping(value = "/withdraw", method = RequestMethod.POST)
	public String update(HttpServletRequest request, @RequestBody Map<String, String> reqMap) {

		String token = request.getHeader(tokenHeader).substring(tokenHead.length());
		String account = jwtTokenUtil.getUsernameFromToken(token);
		JSONObject result = new JSONObject();
		Long nId = -1L;
		try {
			System.out.println(reqMap.get("nId"));
			nId = Long.parseLong(reqMap.get("nId"));

		} catch (Exception e) {
			result.put("state", "fail");
			result.put("msg", "发布失败,请检查参数信息");
			return result.toString();
		}
		News news = newsService.getNewsById(nId);
		news.setUploader(account);
		if (news.getState() == 2) {
			news.setState(1);
			news.setUploader(account);
			Integer i = newsService.updateNews(news);
			if (i == 1) {
				result.put("state", "success");
				result.put("msg", "发布成功");
			} else {
				result.put("state", "fail");
				result.put("msg", "发布失败,请检查参数信息");
			}
		} else {
			result.put("state", "fail");
			result.put("msg", "发布失败,请检查新闻状态");
		}
		return result.toString();
	}

	@PreAuthorize("hasRole('ROLE_ADMIN_01')")
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(HttpServletRequest request, @RequestBody Map<String, String> reqMap) {
		JSONObject result = new JSONObject();
		Long nId = -1L;
		try {
			System.out.println(reqMap.get("nId"));
			nId = Long.parseLong(reqMap.get("nId"));

		} catch (Exception e) {
			result.put("state", "fail");
			result.put("msg", "删除失败,请检查参数信息");
			return result.toString();
		}
		Integer i = newsService.deleteNews(nId);
		if (i == 1) {
			result.put("state", "success");
			result.put("msg", "删除成功");
		} else {
			result.put("state", "fail");
			result.put("msg", "删除失败,请检查参数信息");
		}
		return result.toString();
	}

	@PreAuthorize("hasRole('ROLE_ADMIN_01')")
	@RequestMapping(value = "/getAll", method = RequestMethod.POST)
	public String getAll(HttpServletRequest request, @RequestBody PagingRequestMsg msg) {
		JSONObject result = new JSONObject();

		try {
			List<News> list = newsService.getNewsList(msg);
			JSONArray jList = null;
			if (list.size() > 0) {
				msg.setStart((msg.getPage() - 1) * msg.getLimit());
				Integer toIndex = ((msg.getStart() + msg.getLimit()) < list.size()) ? (msg.getStart() + msg.getLimit())
						: list.size();
				System.out.println(toIndex);
				List<News> pageList = list.subList(msg.getStart(), toIndex);
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

	@RequestMapping(value = "/getById", method = RequestMethod.POST)
	public String getById(HttpServletRequest request, @RequestBody Long nId) {
		JSONObject result = new JSONObject();
		try {
			News news = newsService.getNewsById(nId);
			if (news == null) {
				result.put("state", "fail");
				result.put("msg", "新闻ID不存在");
				return result.toString();
			}
			result.put("state", "success");
			result.put("news", news);
			return result.toString();
		} catch (Exception e) {
			result.put("state", "fail");
			result.put("msg", e);
			e.printStackTrace();
			return result.toString();
		}
	}

}
