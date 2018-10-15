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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import edu.swjtuhc.model.Thesis;
import edu.swjtuhc.service.ThesisService;
import edu.swjtuhc.utils.FileUploadUtil;
import edu.swjtuhc.utils.JwtTokenUtil;
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
			Long attachmentId = Long.parseLong(id);
			String type = params.getParameter("type");
			switch (type) {
			case "thesis":
				Thesis t = thesisService.getThesisById(attachmentId);
				result = FileUploadUtil.saveLocalFile(uploadFile, result, attachmentPath, account);
				if(result.getString("state").equals("success")) {
					t.setAttachment(result.getString("path"));
					t.setUploadDate(new Date());
					thesisService.updateThesis(t);
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
			result.put("state", "fail");
			result.put("msg", "文件ID应为整形");
			return result.toString();
			// TODO: handle exception
		}

	}

	@RequestMapping(value = "/thumbnail", method = RequestMethod.GET)
	public String thumbnail(HttpServletRequest request, @RequestParam("filename") String filename)
			throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		String token = request.getHeader(tokenHeader).substring(tokenHead.length());
		String account = jwtTokenUtil.getUsernameFromToken(token);
		return null;
	}

	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public void getFile(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("filename") String filename) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		String token = request.getHeader(tokenHeader).substring(tokenHead.length());
		String account = jwtTokenUtil.getUsernameFromToken(token);
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename=" + filename);
		byte[] buff = new byte[1024];
		BufferedInputStream bis = null;
		OutputStream os = null;
		try {
			os = response.getOutputStream();
			bis = new BufferedInputStream(new FileInputStream(new File(attachmentPath + account + "/" + filename)));
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

}
