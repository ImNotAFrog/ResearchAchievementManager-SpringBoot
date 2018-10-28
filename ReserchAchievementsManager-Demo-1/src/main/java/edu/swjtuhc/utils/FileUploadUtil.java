package edu.swjtuhc.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import net.sf.json.JSONObject;

public class FileUploadUtil {
    public static String getMimeType(File file) {
        String mimetype = "";
        if (file.exists()) {
            if (FileUploadUtil.getSuffix(file.getName()).equalsIgnoreCase("png")) {
                mimetype = "image/png";
            }else if(FileUploadUtil.getSuffix(file.getName()).equalsIgnoreCase("jpg")){
                mimetype = "image/jpg";
            }else if(FileUploadUtil.getSuffix(file.getName()).equalsIgnoreCase("jpeg")){
                mimetype = "image/jpeg";
            }else if(FileUploadUtil.getSuffix(file.getName()).equalsIgnoreCase("gif")){
                mimetype = "image/gif";
            }else {
                javax.activation.MimetypesFileTypeMap mtMap = new javax.activation.MimetypesFileTypeMap();
                mimetype  = mtMap.getContentType(file);
            }
        }
        return mimetype;
    }



    public static String getSuffix(String filename) {
        String suffix = "";
        int pos = filename.lastIndexOf('.');
        if (pos > 0 && pos < filename.length() - 1) {
            suffix = filename.substring(pos + 1);
        }
        return suffix;
    }
    
    public static String saveLocalImg(MultipartFile imgs,String attachmentPath,Long id) {
    	String fileName = null;
		if (imgs != null) {
			try {
				fileName = new Date().getTime()+"-" + imgs.getOriginalFilename();

				String dirPath = attachmentPath +"news/"+ id + "/";
				String filePath = attachmentPath + "news/"+ id + "/" + fileName;
				File backupDir = new File(dirPath);
				if (!backupDir.exists()) {
					backupDir.mkdirs();
				}
				File backupFile = new File(filePath);
				if (!backupFile.exists()) {
					try {
						backupFile.createNewFile();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				byte[] bytes = imgs.getBytes();
				BufferedOutputStream buffStream = new BufferedOutputStream(
						new FileOutputStream(new File(filePath)));
				buffStream.write(bytes);
				buffStream.close();				

			} catch (Exception e) {
				e.printStackTrace();
				
				return e.getMessage();
			}
		}
		return "/attachment/get?filename=" + fileName;
		
    }
    public static JSONObject saveLocalFile(MultipartFile uploadFile,JSONObject result,String attachmentPath,String account) {
    	
    	String fileName = null;
		if (uploadFile != null) {
			try {
				fileName = new Date().getTime()+"-" + uploadFile.getOriginalFilename();

				String dirPath = attachmentPath + account + "/";
				String filePath = attachmentPath + account + "/" + fileName;
				File backupDir = new File(dirPath);
				if (!backupDir.exists()) {
					backupDir.mkdirs();
				}
				File backupFile = new File(filePath);
				if (!backupFile.exists()) {
					try {
						backupFile.createNewFile();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				byte[] bytes = uploadFile.getBytes();
				BufferedOutputStream buffStream = new BufferedOutputStream(
						new FileOutputStream(new File(filePath)));
				buffStream.write(bytes);
				buffStream.close();
				result.put("state", "success");
				result.put("name", fileName);
				result.put("size", uploadFile.getSize());
				result.put("path", "/attachment/get?filename=" + fileName);
				// result.put("thumbnail", "/attachment/thumbnail?filename=" + fileName);

			} catch (Exception e) {
				e.printStackTrace();
				result.put("state", "fail");
				return result;
			}

			return result;
		} else {
			result.put("state", "fail");
			result.put("msg", "Unable to upload. File is empty.");
			return result;
		}
    }
}
