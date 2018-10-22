package edu.swjtuhc.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import edu.swjtuhc.model.Laws;
import edu.swjtuhc.model.Patent;
import edu.swjtuhc.model.Project;
import edu.swjtuhc.model.ReformProject;
import edu.swjtuhc.model.Textbook;
import edu.swjtuhc.model.Thesis;
import edu.swjtuhc.model.UserProfile;
import edu.swjtuhc.service.LawsService;
import edu.swjtuhc.service.PatentService;
import edu.swjtuhc.service.ProjectService;
import edu.swjtuhc.service.ReformProjectService;
import edu.swjtuhc.service.TextbookService;
import edu.swjtuhc.service.ThesisService;
import edu.swjtuhc.service.UserService;
import edu.swjtuhc.utils.FileUploadUtil;
import edu.swjtuhc.utils.JwtTokenUtil;
import net.sf.json.JSONObject;

@RestController
@RequestMapping("/attachment")
public class AttachmentController {
	@Value("${jwt.header}")
	private String tokenHeader;

	@Value("${jwt.tokenHead}")
	private String tokenHead;

	@Value("${attachemnt.path}")
	private String attachmentPath;

	@Autowired
	private ThesisService thesisService;

	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private TextbookService textbookService;
	
	@Autowired
	private ReformProjectService reformProjectService;
	
	@Autowired
	private PatentService patentService;
	
	@Autowired
	private LawsService lawsService;

	@Autowired
	private UserService userService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();

	@RequestMapping(value = "/upload", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String upload(HttpServletRequest request) throws Exception {

		request.setCharacterEncoding("UTF-8");
		String token = request.getHeader(tokenHeader).substring(tokenHead.length());
		String account = jwtTokenUtil.getUsernameFromToken(token);

		JSONObject result = new JSONObject();
		MultipartHttpServletRequest params = ((MultipartHttpServletRequest) request);
		MultipartFile uploadFile = params.getFile("file");
		String id = params.getParameter("id");
		try {
			UserProfile user = userService.getUserProfileByAccount(account);
			if (user == null) {
				result.put("state", "fail");
				result.put("msg", "用户权限错误");
				return result.toString();
			}
			Long attachmentId = Long.parseLong(id);
			String type = params.getParameter("type");
			switch (type) {
			case "thesis":
				Thesis t = thesisService.getThesisById(attachmentId);
				result = FileUploadUtil.saveLocalFile(uploadFile, result, attachmentPath, account);
				if (t == null) {
					result = new JSONObject();
					result.put("state", "fail");
					result.put("msg", "论文成果不存在");
				} else if (result.getString("state").equals("success")) {
					t.setAttachment(result.getString("name"));
					int i = thesisService.appendAttachment(t);
					if (i != 1) {
						result = new JSONObject();
						result.put("state", "fail");
						result.put("msg", "附件上传失败");
					}
				}
				break;
			case "textbook":
				Textbook tb = textbookService.getTextbookById(attachmentId);
				result = FileUploadUtil.saveLocalFile(uploadFile, result, attachmentPath, account);
				if (tb == null) {
					result = new JSONObject();
					result.put("state", "fail");
					result.put("msg", "论著成果不存在");
				} else if (result.getString("state").equals("success")) {
					tb.setAttachment(result.getString("name"));
					int i = textbookService.appendAttachment(tb);
					if (i != 1) {
						result = new JSONObject();
						result.put("state", "fail");
						result.put("msg", "附件上传失败");
					}
				}
				break;
			case "patent":
				Patent pa = patentService.getPatentById(attachmentId);
				result = FileUploadUtil.saveLocalFile(uploadFile, result, attachmentPath, account);
				if (pa == null) {
					result = new JSONObject();
					result.put("state", "fail");
					result.put("msg", "专利成果不存在");
				} else if (result.getString("state").equals("success")) {
					pa.setAttachment(result.getString("name"));
					int i = patentService.appendAttachment(pa);
					if (i != 1) {
						result = new JSONObject();
						result.put("state", "fail");
						result.put("msg", "附件上传失败");
					}
				}
				break;
			case "project":
				Project p = projectService.getProjectById(attachmentId);
				result = FileUploadUtil.saveLocalFile(uploadFile, result, attachmentPath, account);
				if (p == null) {
					result = new JSONObject();
					result.put("state", "fail");
					result.put("msg", "课题项目不存在");
				} else if (result.getString("state").equals("success")) {
					p.setAttachment(result.getString("name"));
					int i = projectService.appendAttachment(p);
					if (i != 1) {
						result = new JSONObject();
						result.put("state", "fail");
						result.put("msg", "附件上传失败");
					}
				}
				break;
			case "reformProject":
				ReformProject rp = reformProjectService.getReformProjectById(attachmentId);
				result = FileUploadUtil.saveLocalFile(uploadFile, result, attachmentPath, account);
				if (rp == null) {
					result = new JSONObject();
					result.put("state", "fail");
					result.put("msg", "教改项目不存在");
				} else if (result.getString("state").equals("success")) {
					rp.setAttachment(result.getString("name"));
					int i = reformProjectService.appendAttachment(rp);
					if (i != 1) {
						result = new JSONObject();
						result.put("state", "fail");
						result.put("msg", "附件上传失败");
					}
				}
				break;
			case "laws":
				Laws l = lawsService.getLawsById(attachmentId);
				result = FileUploadUtil.saveLocalFile(uploadFile, result, attachmentPath, account);
				if (l == null) {
					result = new JSONObject();
					result.put("state", "fail");
					result.put("msg", "法律、法规成果不存在");
				} else if (result.getString("state").equals("success")) {
					l.setAttachment(result.getString("name"));
					int i = lawsService.appendAttachment(l);
					if (i != 1) {
						result = new JSONObject();
						result.put("state", "fail");
						result.put("msg", "附件上传失败");
					}
				}
				break;
			default:
				result.put("state", "fail");
				result.put("msg", "文件类型参数错误");
				break;
			}
			return result.toString();
		} catch (Exception e) {
			e.printStackTrace();
			result = new JSONObject();
			result.put("state", "fail");
			result.put("msg", "文件ID应为整形");
			return result.toString();
			// TODO: handle exception
		}

	}

	@RequestMapping(value = "/get/file", method = RequestMethod.GET)
	public void getFile(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("filename") String filename, @RequestParam("account") String account)
			throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		response.setHeader("Content-Transfer-Encoding", "binary");
		response.setHeader("Content-Description", "File Transfer");
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename=" + filename);
		File file = new File(attachmentPath + account + "/" + filename);
		response.setHeader("Cache-Control", "public");
		response.setContentLengthLong(file.length());
		byte[] buff = new byte[1024];
		BufferedInputStream bis = null;
		OutputStream os = null;
		try {
			os = response.getOutputStream();
			bis = new BufferedInputStream(new FileInputStream(file));
			int i = bis.read(buff);
			while (i != -1) {
				os.write(buff, 0, buff.length);
				os.flush();
				i = bis.read(buff);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bis != null) {
				try {
					bis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(HttpServletRequest request, @RequestBody Map<String, Object> reqMap) {
		String token = request.getHeader(tokenHeader).substring(tokenHead.length());
		String account = jwtTokenUtil.getUsernameFromToken(token);

		String filename = (String) reqMap.get("filename");
		Long aId = 0L;
		String type = (String) reqMap.get("type");
		JSONObject result = new JSONObject();
		try {
			aId = (Long) reqMap.get("aId");
		} catch (Exception e) {
			// TODO: handle exception
			result.put("state", "fail");
			result.put("msg", "Id应为整型");
			e.printStackTrace();
			return result.toString();
		}
		String filepath=null;
		File file=null;
		if (filename != null && aId != null && type != null) {
			switch (type) {
			case "thesis":
				Thesis t = thesisService.getThesisById(aId);
				if (t==null||t.getUploader() == null || !t.getUploader().equals(account)) {
					result.put("state", "fail");
					result.put("msg", "论文成果或用户权限错误");
					return result.toString();
				}
				filepath = attachmentPath + t.getUploader() + "/" + filename;
				file = new File(filepath);
				if (file.delete()) {
					try {
						t.setAttachment(filename);
						if (thesisService.removeAttachment(t) == 1) {
							result.put("state", "success");
							result.put("msg", "删除成功");
						} else {
							result.put("state", "success");
							result.put("msg", "数据库更新失败或文件不存在");
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						result.put("state", "success");
						result.put("msg", "数据库更新失败");
						e.printStackTrace();
						return request.toString();
					}
				} else {
					result.put("state", "failed");
					result.put("msg", "删除失败，文件或不存在");
				}
				break;
			case "textbook":
				Textbook tb = textbookService.getTextbookById(aId);
				if (tb==null||tb.getUploader() == null || !tb.getUploader().equals(account)) {
					result.put("state", "fail");
					result.put("msg", "论著成果或用户权限错误");
					return result.toString();
				}
				filepath = attachmentPath + tb.getUploader() + "/" + filename;
				file = new File(filepath);
				if (file.delete()) {
					try {
						tb.setAttachment(filename);
						if (textbookService.removeAttachment(tb) == 1) {
							result.put("state", "success");
							result.put("msg", "删除成功");
						} else {
							result.put("state", "success");
							result.put("msg", "数据库更新失败或文件不存在");
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						result.put("state", "success");
						result.put("msg", "数据库更新失败");
						e.printStackTrace();
						return request.toString();
					}
				} else {
					result.put("state", "failed");
					result.put("msg", "删除失败，文件或不存在");
				}
				break;
			case "patent":
				Patent pa = patentService.getPatentById(aId);
				if (pa==null||pa.getUploader() == null || !pa.getUploader().equals(account)) {
					result.put("state", "fail");
					result.put("msg", "专利成果或用户权限错误");
					return result.toString();
				}
				filepath = attachmentPath + pa.getUploader() + "/" + filename;
				file = new File(filepath);
				if (file.delete()) {
					try {
						pa.setAttachment(filename);
						if (patentService.removeAttachment(pa) == 1) {
							result.put("state", "success");
							result.put("msg", "删除成功");
						} else {
							result.put("state", "success");
							result.put("msg", "数据库更新失败或文件不存在");
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						result.put("state", "success");
						result.put("msg", "数据库更新失败");
						e.printStackTrace();
						return request.toString();
					}
				} else {
					result.put("state", "failed");
					result.put("msg", "删除失败，文件或不存在");
				}
				break;
			case "project":
				Project p = projectService.getProjectById(aId);
				if (p==null||p.getUploader() == null || !p.getUploader().equals(account)) {
					result.put("state", "fail");
					result.put("msg", "课题项目或用户权限错误");
					return result.toString();
				}
				filepath = attachmentPath + p.getUploader() + "/" + filename;
				file = new File(filepath);
				if (file.delete()) {
					try {
						p.setAttachment(filename);
						if (projectService.removeAttachment(p) == 1) {
							result.put("state", "success");
							result.put("msg", "删除成功");
						} else {
							result.put("state", "success");
							result.put("msg", "数据库更新失败或文件不存在");
						}
						;
					} catch (Exception e) {
						// TODO Auto-generated catch block
						result.put("state", "success");
						result.put("msg", "数据库更新失败");
						e.printStackTrace();
						return request.toString();
					}
				} else {
					result.put("state", "failed");
					result.put("msg", "删除失败，文件或不存在");
				}
				break;
			case "reformProject":
				ReformProject rp = reformProjectService.getReformProjectById(aId);
				if (rp==null||rp.getUploader() == null || !rp.getUploader().equals(account)) {
					result.put("state", "fail");
					result.put("msg", "教改项目或用户权限错误");
					return result.toString();
				}
				filepath = attachmentPath + rp.getUploader() + "/" + filename;
				file = new File(filepath);
				if (file.delete()) {
					try {
						rp.setAttachment(filename);
						if (reformProjectService.removeAttachment(rp) == 1) {
							result.put("state", "success");
							result.put("msg", "删除成功");
						} else {
							result.put("state", "success");
							result.put("msg", "数据库更新失败或文件不存在");
						}
						;
					} catch (Exception e) {
						// TODO Auto-generated catch block
						result.put("state", "success");
						result.put("msg", "数据库更新失败");
						e.printStackTrace();
						return request.toString();
					}
				} else {
					result.put("state", "failed");
					result.put("msg", "删除失败，文件或不存在");
				}
				break;
			case "laws":
				Laws l = lawsService.getLawsById(aId);
				if (l==null||l.getUploader() == null || !l.getUploader().equals(account)) {
					result.put("state", "fail");
					result.put("msg", "法律、法规成果或用户权限错误");
					return result.toString();
				}
				filepath = attachmentPath + l.getUploader() + "/" + filename;
				file = new File(filepath);
				if (file.delete()) {
					try {
						l.setAttachment(filename);
						if (lawsService.removeAttachment(l) == 1) {
							result.put("state", "success");
							result.put("msg", "删除成功");
						} else {
							result.put("state", "success");
							result.put("msg", "数据库更新失败或文件不存在");
						}
						;
					} catch (Exception e) {
						// TODO Auto-generated catch block
						result.put("state", "success");
						result.put("msg", "数据库更新失败");
						e.printStackTrace();
						return request.toString();
					}
				} else {
					result.put("state", "failed");
					result.put("msg", "删除失败，文件或不存在");
				}
				break;
			default:
				result.put("state", "fail");
				result.put("msg", "文件类型参数错误");
				break;
			}

		} else {
			result.put("state", "fail");
			result.put("msg", "请检查参数");
		}

		return result.toString();
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN_01','ROLE_ADMIN_02')")
	@RequestMapping(value = "/adminDelete", method = RequestMethod.POST)
	public String adminDelete(HttpServletRequest request, @RequestBody Map<String, Object> reqMap) {
		String filename = (String) reqMap.get("filename");
		Long aId = 0L;
		String type = (String) reqMap.get("type");
		JSONObject result = new JSONObject();
		try {
			aId = (Long) reqMap.get("aId");
		} catch (Exception e) {
			// TODO: handle exception
			result.put("state", "fail");
			result.put("msg", "Id应为整型");
			e.printStackTrace();
			return result.toString();
		}
		String filepath=null;
		File file=null;
		if (filename != null && aId != null && type != null) {
			switch (type) {
			case "thesis":
				Thesis t = thesisService.getThesisById(aId);
				if (t==null) {
					result.put("state", "fail");
					result.put("msg", "论文成果不存在");
					return result.toString();
				}
				filepath = attachmentPath + t.getUploader() + "/" + filename;
				file = new File(filepath);
				if (file.delete()) {
					try {
						t.setAttachment(filename);
						if (thesisService.removeAttachment(t) == 1) {
							result.put("state", "success");
							result.put("msg", "删除成功");
						} else {
							result.put("state", "success");
							result.put("msg", "数据库更新失败或文件不存在");
						}
						;
					} catch (Exception e) {
						// TODO Auto-generated catch block
						result.put("state", "success");
						result.put("msg", "数据库更新失败");
						e.printStackTrace();
						return request.toString();
					}
				} else {
					result.put("state", "failed");
					result.put("msg", "删除失败，文件或不存在");
				}
				break;
			case "textbook":
				Textbook tb = textbookService.getTextbookById(aId);
				if (tb==null) {
					result.put("state", "fail");
					result.put("msg", "论著成果不存在");
					return result.toString();
				}
				filepath = attachmentPath + tb.getUploader() + "/" + filename;
				file = new File(filepath);
				if (file.delete()) {
					try {
						tb.setAttachment(filename);
						if (textbookService.removeAttachment(tb) == 1) {
							result.put("state", "success");
							result.put("msg", "删除成功");
						} else {
							result.put("state", "success");
							result.put("msg", "数据库更新失败或文件不存在");
						}
						;
					} catch (Exception e) {
						// TODO Auto-generated catch block
						result.put("state", "success");
						result.put("msg", "数据库更新失败");
						e.printStackTrace();
						return request.toString();
					}
				} else {
					result.put("state", "failed");
					result.put("msg", "删除失败，文件或不存在");
				}
				break;
			case "patent":
				Patent pa = patentService.getPatentById(aId);
				if (pa==null) {
					result.put("state", "fail");
					result.put("msg", "专利成果不存在");
					return result.toString();
				}
				filepath = attachmentPath + pa.getUploader() + "/" + filename;
				file = new File(filepath);
				if (file.delete()) {
					try {
						pa.setAttachment(filename);
						if (patentService.removeAttachment(pa) == 1) {
							result.put("state", "success");
							result.put("msg", "删除成功");
						} else {
							result.put("state", "success");
							result.put("msg", "数据库更新失败或文件不存在");
						}
						;
					} catch (Exception e) {
						// TODO Auto-generated catch block
						result.put("state", "success");
						result.put("msg", "数据库更新失败");
						e.printStackTrace();
						return request.toString();
					}
				} else {
					result.put("state", "failed");
					result.put("msg", "删除失败，文件或不存在");
				}
				break;
			case "project":
				Project p = projectService.getProjectById(aId);
				if (p==null) {
					result.put("state", "fail");
					result.put("msg", "课题项目不存在");
					return result.toString();
				}
				filepath = attachmentPath + p.getUploader() + "/" + filename;
				file = new File(filepath);
				if (file.delete()) {
					try {
						p.setAttachment(filename);
						if (projectService.removeAttachment(p) == 1) {
							result.put("state", "success");
							result.put("msg", "删除成功");
						} else {
							result.put("state", "success");
							result.put("msg", "数据库更新失败或文件不存在");
						}
						;
					} catch (Exception e) {
						// TODO Auto-generated catch block
						result.put("state", "success");
						result.put("msg", "数据库更新失败");
						e.printStackTrace();
						return request.toString();
					}
				} else {
					result.put("state", "failed");
					result.put("msg", "删除失败，文件或不存在");
				}
				break;
			case "reformProject":
				ReformProject rp = reformProjectService.getReformProjectById(aId);
				if (rp==null) {
					result.put("state", "fail");
					result.put("msg", "教改项目不存在");
					return result.toString();
				}
				filepath = attachmentPath + rp.getUploader() + "/" + filename;
				file = new File(filepath);
				if (file.delete()) {
					try {
						rp.setAttachment(filename);
						if (reformProjectService.removeAttachment(rp) == 1) {
							result.put("state", "success");
							result.put("msg", "删除成功");
						} else {
							result.put("state", "success");
							result.put("msg", "数据库更新失败或文件不存在");
						}
						;
					} catch (Exception e) {
						// TODO Auto-generated catch block
						result.put("state", "success");
						result.put("msg", "数据库更新失败");
						e.printStackTrace();
						return request.toString();
					}
				} else {
					result.put("state", "failed");
					result.put("msg", "删除失败，文件或不存在");
				}
				break;
			case "laws":
				Laws l = lawsService.getLawsById(aId);
				if (l==null) {
					result.put("state", "fail");
					result.put("msg", "法律、法规成果不存在");
					return result.toString();
				}
				filepath = attachmentPath + l.getUploader() + "/" + filename;
				file = new File(filepath);
				if (file.delete()) {
					try {
						l.setAttachment(filename);
						if (lawsService.removeAttachment(l) == 1) {
							result.put("state", "success");
							result.put("msg", "删除成功");
						} else {
							result.put("state", "success");
							result.put("msg", "数据库更新失败或文件不存在");
						}
						;
					} catch (Exception e) {
						// TODO Auto-generated catch block
						result.put("state", "success");
						result.put("msg", "数据库更新失败");
						e.printStackTrace();
						return request.toString();
					}
				} else {
					result.put("state", "failed");
					result.put("msg", "删除失败，文件或不存在");
				}
				break;
			default:
				result.put("state", "fail");
				result.put("msg", "文件类型参数错误");
				break;
			}

		} else {
			result.put("state", "fail");
			result.put("msg", "请检查参数");
		}

		return result.toString();
	}
}
