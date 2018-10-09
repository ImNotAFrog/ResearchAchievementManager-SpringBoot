package edu.swjtuhc.controller;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import edu.swjtuhc.utils.FileUploadUtil;
import edu.swjtuhc.utils.JwtTokenUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@RestController
public class AttachmentController {
    @Value("${jwt.header}")
    private String tokenHeader;
    
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    
    @Autowired
    private JwtTokenUtil jwtTokenUtil=new JwtTokenUtil();
    
	@RequestMapping(value = "/attachmentUpload" ,method = RequestMethod.POST)
    public void uploadApkFile(HttpServletRequest request,HttpServletResponse response)
            throws Exception {
        request.setCharacterEncoding("UTF-8");
    	String token = request.getHeader(tokenHeader).substring(tokenHead.length());
        String account = jwtTokenUtil.getUsernameFromToken(token);
        if (!ServletFileUpload.isMultipartContent(request)) {
            throw new IllegalArgumentException("Request is not multipart, please 'multipart/form-data' enctype for your form.");
        }
        ServletFileUpload uploadHandler = new ServletFileUpload(new DiskFileItemFactory());
        PrintWriter writer = response.getWriter();
        response.setContentType("application/json");
        JSONArray json = new JSONArray();
        try {
            List<FileItem> items = uploadHandler.parseRequest((RequestContext) request);
            for (FileItem item : items) {
                if (!item.isFormField()&&!item.getName().equals("")) {
                		SimpleDateFormat sdf3 = new SimpleDateFormat("yyyyMMddHHmmss");  	                   
                		String fileName =  sdf3.format(new Date())+item.getName();
                		fileName=fileName.replace(" ", "_");
	                	String dirPath = request.getServletContext().getRealPath("/")+"META-INF/Attachments/"+account+"/";
	                    String filePath = request.getServletContext().getRealPath("/")+"META-INF/Attachments/"+account+"/"+fileName;
                        String backupDirPath = "D:\\Attachments/"+account+"/";
                        String backupFilePath = "D:\\Attachments/"+account+"/"+fileName;
                        
	                    File dir = new File(dirPath);
                        if (!dir.exists()) {
                        	dir.mkdirs();
                        }
                        File file = new File(filePath);
                        if (!file.exists()) {
                            try {
                            	file.createNewFile();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        File backupDir = new File(backupDirPath);
                        if (!backupDir.exists()) {
                        	backupDir.mkdirs();
                        }
                        File backupFile = new File(backupFilePath);
                        if (!backupFile.exists()) {
                            try {
                            	backupFile.createNewFile();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        
                        
                        try {
							item.write(file);
							item.write(backupFile);
						} catch (Exception e) {
							// TODO Auto-generated catch block
						}
                        JSONObject jsono = new JSONObject();
                        jsono.put("name", item.getName());
                        jsono.put("size", item.getSize());
                        jsono.put("delete_type", "GET");
                        System.out.println(account+"：文件"+fileName+"上传成功");
                }
            }
        } catch (FileUploadException e) {
                throw new RuntimeException(e);
        } catch (Exception e) {
                throw new RuntimeException(e);
        } finally {
            writer.write(json.toString());
            writer.close();
        }
    }
}
