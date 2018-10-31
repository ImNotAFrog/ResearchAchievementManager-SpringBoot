package edu.swjtuhc.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.filechooser.FileSystemView;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.swjtuhc.config.AppConfigs;
import edu.swjtuhc.model.Achievement;
import edu.swjtuhc.model.DepartmentRank;
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

	@PreAuthorize("hasRole('ROLE_ADMIN_01')")
	@RequestMapping(value = "/department", method = RequestMethod.POST)
	public String department(HttpServletRequest request, @RequestBody RankingRequestMsg msg) {
		JSONObject result = new JSONObject();
		try {
			List<DepartmentRank> list = rankingService.department(msg);
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

	@PreAuthorize("hasRole('ROLE_ADMIN_01')")
	@RequestMapping(value = "/download/individualRank", method = RequestMethod.POST)
	public String downloadIndividualRank(HttpServletRequest request, HttpServletResponse response,
			@RequestBody RankingRequestMsg msg) {
		JSONObject result = new JSONObject();
		try {
			List<IndividualRank> list = rankingService.individual(msg);
			XSSFWorkbook wb = new XSSFWorkbook();
			// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
			XSSFSheet sheet = wb.createSheet("导出个人成果排名");
			;
			// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
			XSSFRow row = sheet.createRow((int) 0);
			;
			// 第四步，创建单元格，并设置值表头 设置表头居中
			XSSFCellStyle style = wb.createCellStyle();

			XSSFCell cell = row.createCell((short) 0);

			cell.setCellValue("排名");
			cell.setCellStyle(style);

			cell = row.createCell((short) 1);
			cell.setCellValue("姓名");
			cell.setCellStyle(style);

			cell = row.createCell((short) 2);
			cell.setCellValue("成果总量");
			cell.setCellStyle(style);

			cell = row.createCell((short) 3);
			cell.setCellValue("总分");
			cell.setCellStyle(style);

			cell = row.createCell((short) 4);
			cell.setCellValue("所属部门");
			cell.setCellStyle(style);

			cell = row.createCell((short) 5);
			cell.setCellValue("所属子部门");
			cell.setCellStyle(style);
			for (int i = 0; i < list.size(); i++) {
				row = sheet.createRow((int) i + 1);
				IndividualRank rank = list.get(i);
				// 第四步，创建单元格，并设置值
				row.createCell((short) 0).setCellValue(rank.getRank());
				row.createCell((short) 1).setCellValue(rank.getName());
				row.createCell((short) 2).setCellValue(rank.getCount());
				row.createCell((short) 3).setCellValue(rank.getScore());
				row.createCell((short) 4).setCellValue(rank.getDepartment());
				row.createCell((short) 5).setCellValue(rank.getSubDepartment());
			}
			String p = AppConfigs.class.getResource("/").getPath();
			String myDir = p.substring(1, p.indexOf("classes"));
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");  
            String startDateString = formatter.format(msg.getStartDate()); 
            String endDateString = formatter.format(msg.getEndDate()); 
			String deskPath = myDir + "/rankExport/"+startDateString+"-"+endDateString+"Ranking.xlsx";
			FileOutputStream fout = new FileOutputStream(new File(deskPath));
			wb.write(fout);
			fout.close();
			response.setContentType(request.getServletContext().getMimeType(deskPath));
			response.setHeader("Content-Disposition", "attachment;filename=" +startDateString+"-"+endDateString+"Ranking.xlsx");
			// out.print("<script
			// type='text/javascript'charset='utf-8'>alert('下载')</script>");
			FileInputStream fin = new FileInputStream(deskPath);
			int b;
			while ((b = fin.read()) != -1) {
				response.getWriter().write(b);
			}
			fin.close();
		} catch (Exception e) {
			// TODO: handle exception
			result = new JSONObject();
			result.put("state", "fail");
			result.put("msg", e);
			e.printStackTrace();
		}
		return result.toString();
	}
	@PreAuthorize("hasRole('ROLE_ADMIN_01')")
	@RequestMapping(value = "/download/individualDetail", method = RequestMethod.POST)
	public String downloadIndividualDetail(HttpServletRequest request, HttpServletResponse response,
			@RequestBody RankingRequestMsg msg) {
		JSONObject result = new JSONObject();
		if(msg.getAccount()==null) {
			result.put("state", "fail");
			result.put("msg", "未指定导出用户");
			return result.toString();
		}
		try {
			List<IndividualRank> list = rankingService.individual(msg);
			XSSFWorkbook wb = new XSSFWorkbook();
			// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
			XSSFSheet sheet = wb.createSheet("导出个人成果明细");
			;
			// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
			XSSFRow row = sheet.createRow((int) 0);
			;
			// 第四步，创建单元格，并设置值表头 设置表头居中
			XSSFCellStyle style = wb.createCellStyle();

			XSSFCell cell = row.createCell((short) 0);

			wb = new XSSFWorkbook();  
	        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet 
	        sheet = wb.createSheet("导出个人成果明细");  
	        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short  
	        row = sheet.createRow((int) 0);  
	        // 第四步，创建单元格，并设置值表头 设置表头居中  
	        style = wb.createCellStyle(); 
	        cell = row.createCell((short) 0);  
	        cell.setCellValue("姓名");  
	        cell.setCellStyle(style);  
	        
	        cell = row.createCell((short) 1);  
	        cell.setCellValue("成果名称");  
	        cell.setCellStyle(style);  
	  
	        cell = row.createCell((short) 2);  
	        cell.setCellValue("类型");  
	        cell.setCellStyle(style);  
	  
	        cell = row.createCell((short) 3);  
	        cell.setCellValue("得分");  
	        cell.setCellStyle(style);  
	  
	        cell = row.createCell((short) 4);  
	        cell.setCellValue("所属部门");  
	        cell.setCellStyle(style);  
	        
	        cell = row.createCell((short) 5);  
	        cell.setCellValue("所属科室");  
	        cell.setCellStyle(style); 
	        
	        cell = row.createCell((short) 6);  
	        cell.setCellValue("日期");  
	        cell.setCellStyle(style); 
	        IndividualRank rank = null;
	        for (int i = 0; i < list.size(); i++) {
				rank = list.get(i);
				if(rank.getAccount().equals(msg.getAccount())) {
					break;
				}
			}
	        List<Achievement> al = rank.getAchievementList();
			for (int i = 0; i < al.size(); i++) {
				row = sheet.createRow((int) i + 1);
				Achievement a = al.get(i);
				// 第四步，创建单元格，并设置值
				String cate;
	            switch(a.getType()){
	            	case "thesis":
	            		cate = "论文";
	            		break;
	            	case "eduProject":
	            		cate = "课题项目";
	            		break;
	            	case "textbook":
	            		cate = "教材、论著";
	            		break;
	            	case "patent":
	            		cate = "专利";
	            		break;
	            	case "laws":
	            		cate = "法律、法规";
	            		break;
	            		default:
	            			cate = "教改项目";
	            			break;
	            }
				row.createCell((short) 0).setCellValue(a.getUploaderName());
				row.createCell((short) 1).setCellValue(a.getName());
				row.createCell((short) 2).setCellValue(cate);
				row.createCell((short) 3).setCellValue(a.getScore());
				row.createCell((short) 4).setCellValue(a.getDepartment());
				row.createCell((short) 5).setCellValue(a.getSubDepartment());
				row.createCell((short) 5).setCellValue(a.getValidDate());
				
			}
			String p = AppConfigs.class.getResource("/").getPath();
			String myDir = p.substring(1, p.indexOf("classes"));
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");  
            String startDateString = formatter.format(msg.getStartDate()); 
            String endDateString = formatter.format(msg.getEndDate()); 
			String deskPath = myDir + "/rankExport/"+startDateString+"-"+endDateString+"Ranking.xlsx";
			FileOutputStream fout = new FileOutputStream(new File(deskPath));
			wb.write(fout);
			fout.close();
			response.setContentType(request.getServletContext().getMimeType(deskPath));
			response.setHeader("Content-Disposition", "attachment;filename="+startDateString+"-"+endDateString+"Ranking.xlsx");
			// out.print("<script
			// type='text/javascript'charset='utf-8'>alert('下载')</script>");
			FileInputStream fin = new FileInputStream(deskPath);
			int b;
			while ((b = fin.read()) != -1) {
				response.getWriter().write(b);
			}
			fin.close();
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
