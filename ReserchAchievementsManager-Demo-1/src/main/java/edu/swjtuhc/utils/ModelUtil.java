package edu.swjtuhc.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.swjtuhc.model.Achievement;
import edu.swjtuhc.model.Thesis;

public class ModelUtil {
	public static Object updateModelByModel(Object origin, Object newModel) {
		if(!origin.getClass().equals(newModel.getClass())) {
			System.err.println("model type not match!");
			return origin;
		}
		Field[] newFields = newModel.getClass().getDeclaredFields();
		for (int i = 0; i < newFields.length; i++) {
			Field f = newFields[i];
			f.setAccessible(true);
			try {
				if (f.get(newModel) != null) {
					f.set(origin, f.get(newModel));
				}
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return origin;
	}
	public static String appendPath(String path1,String path2) {
		if(path1==null) {
			return path2;
		}
		return path1+"|"+path2;
	}
	public static String deletePath(String path1,String path2) {
		if(path1==null) {
			return null;
		}
		String[] paths = path1.split("\\|");
		if(paths.length==0) {
			return path1;
		}
		List<String> list = new ArrayList<>(Arrays.asList(paths));
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).equals(path2)) {
				list.remove(i);		
				break;
			}
		}	
		if(list.size()==0) {
			return null;
		}
		return String.join("|", list);
	}
	public static void main(String[] args) {
		Thesis t = new Thesis();
		t.setAttachment("1539739477819-5.jpg");
		String s = deletePath(t.getAttachment(), "1539739477819-5.jpg");
		System.out.println(s);
	}
}
