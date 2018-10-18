package edu.swjtuhc.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import edu.swjtuhc.model.Thesis;
import edu.swjtuhc.model.UserProfile;
import edu.swjtuhc.service.ThesisService;
import edu.swjtuhc.service.UserService;
import edu.swjtuhc.utils.FileUploadUtil;
import edu.swjtuhc.utils.JwtTokenUtil;
import edu.swjtuhc.utils.ModelUtil;
import net.sf.json.JSONArray;
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
			if(user==null) {
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
				if(t==null) {
					result=new JSONObject();
					result.put("state", "fail");
					result.put("msg", "论文不存在");
				}
				else if(result.getString("state").equals("success")) {					
					t.setAttachment(ModelUtil.appendPath(t.getAttachment(), result.getString("name")));
					thesisService.updateThesis(t,user);
				}
				break;
			case "textbook":

				break;
			case "patent":

				break;
			case "project":

				break;
			case "reformProject":

				break;
			case "law":

				break;
			default:
				result.put("state", "fail");
				result.put("msg", "文件类型参数错误");
				break;
			}
			return result.toString();
		} catch (Exception e) {
			e.printStackTrace();
			result=new JSONObject();
			result.put("state", "fail");
			result.put("msg", "文件ID应为整形");
			return result.toString();
			// TODO: handle exception
		}

	}

	@RequestMapping(value = "/get/file", method = RequestMethod.GET)
	public void getFile(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("filename") String filename,@RequestParam("account") String account) throws UnsupportedEncodingException {
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
	public String delete(HttpServletRequest request,@RequestBody Map<String,Object> reqMap) {
		String token = request.getHeader(tokenHeader).substring(tokenHead.length());
		String account = jwtTokenUtil.getUsernameFromToken(token);
		String filename=(String) reqMap.get("filename");
		Long aId=0L;		
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
		
		if(filename!=null&&aId!=null&&type!=null) {
			switch (type) {
			case "thesis":
				Thesis t =thesisService.getThesisById(aId);
				System.out.println(t.getUploader());
				System.out.println(account);
				if(t.getUploader()==null||!t.getUploader().equals(account)) {
					result.put("state", "fail");
					result.put("msg", "论文或用户权限错误");
					return result.toString();
				}
				String filepath = attachmentPath + t.getUploader() + "/" + filename;
				File file = new File(filepath);
	            if (file.delete()) {
	            	try {
	            		t.setAttachment(ModelUtil.deletePath(t.getAttachment(), filename));
						if(thesisService.modifyThesis(t)==1) {
			            	result.put("state", "success");
							result.put("msg", "删除成功");
						}else{
							result.put("state", "success");
							result.put("msg", "数据库更新失败或文件不存在");
						};
					} catch (IOException e) {
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

				break;
			case "patent":

				break;
			case "project":

				break;
			case "reformProject":

				break;
			case "law":

				break;
			default:
				result.put("state", "fail");
				result.put("msg", "文件类型参数错误");
				break;
			}
			
		}else {
			result.put("state", "fail");
			result.put("msg", "请检查参数");
		}
		
		return result.toString();
	}

}
