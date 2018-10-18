package edu.swjtuhc.utils;

import java.io.FileInputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@WebListener
public class AppConfigs implements ServletContextListener{
	public static ArrayList<Double> SCORES = new ArrayList();
	public static void load(){
		FileInputStream inputStream;
		String p = AppConfigs.class.getResource("/").getPath();  
		try {
			inputStream = new FileInputStream(p  +"/scores/Scores.xlsx");
			
		    //读取工作簿  
		    XSSFWorkbook workBook = new XSSFWorkbook(inputStream);  
		    //读取工作表  
		    XSSFSheet sheet = workBook.getSheetAt(0);  
		    //读取行  
		    for(int i =3;i<47;i++){
		    	XSSFRow row = sheet.getRow(i); 
		    	 //读取单元格  
			    XSSFCell cell = row.getCell(5);  
			    double value = cell.getNumericCellValue();  
			    System.out.println(value);
			    SCORES.add(value);
		    }
            
		    inputStream.close();  
		    workBook.close();//最后记得关闭工作簿  
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		     
        
	}
	
	public static void startWatching(){
		Thread t = new Thread(new Runnable(){  
    	    //2):在A类中覆盖Runnable接口中的run方法.  
    		String p = AppConfigs.class.getResource("/").getPath();  
    		Path myDir = Paths.get(p.substring(1)+"scores"); 
    	    public void run() {  
    	    	try {  
    	            WatchService watcher = myDir.getFileSystem().newWatchService();  
    	            myDir.register(watcher, StandardWatchEventKinds.ENTRY_CREATE,   
    	            StandardWatchEventKinds.ENTRY_DELETE, StandardWatchEventKinds.ENTRY_MODIFY);  
    	            WatchKey watckKey = watcher.take();  
    	            List<WatchEvent<?>> events = watckKey.pollEvents();  
    	            for (WatchEvent event : events) {  
    	                 if (event.kind() == StandardWatchEventKinds.ENTRY_CREATE) {  
    	                     System.out.println("Created: " + event.context().toString());  
    	                     load();
    	                 }  
    	                 if (event.kind() == StandardWatchEventKinds.ENTRY_DELETE) {  
    	                     System.out.println("Delete: " + event.context().toString());  
    	                     load();
    	                 }  
    	                 if (event.kind() == StandardWatchEventKinds.ENTRY_MODIFY) {  
    	                 	System.out.println("Modify: " + event.context().toString());  
    	                 	load();                    
    	                 }  
    	             }  
    	         } catch (Exception e) {  
    	             System.out.println("Error: " + e.toString());  
    	         }  
    	    }  
    	});  
        t.start();  
	}
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		load();
		startWatching();
	}
	
	
	 
}
