package edu.swjtuhc.enums;

import java.util.Arrays;
import java.util.List;

public enum Title {
	PROFESSOR("教授","PROFESSOR"),ASSOCIATE_PROFESSOR("副教授","ASSOCIATE_PROFESSOR"),LECTURER("讲师","LECTURER")
	,ASSISTANT("助教","ASSISTANT"),TECHNICIAN("技术员","TECHNICIAN"),ASSISTANT_ENGINEER("助理工程师","ASSISTANT_ENGINEER")
	,ENGINEER("工程师","ENGINEER"),SENIOR_ENGINEER("高级工程师","SENIOR_ENGINEER");
	private String name;
	private String index;
	private Title(String name, String index) {
		this.name=name;
		this.index =index;
		// TODO Auto-generated constructor stub
	}
	
	 public static String getName(String index) {
         for (Title t : Title.values()) {
             if (t.getIndex() == index) {
                 return t.name;
             }
         }
         return null;
     }
	 
	 public static String getIndex(String name) {
         for (Title t : Title.values()) {
             if (t.getName().equals(name)) {
                 return t.index;
             }
         }
         return null;
     }
	 public static List<Title> getList(){
		 List<Title> l = Arrays.asList(Title.values());
		 return l;
	 }
     // get set 方法
     public String getName() {
         return name;
     }

     public void setName(String name) {
         this.name = name;
     }

     public String getIndex() {
         return index;
     }

     public void setIndex(String index) {
         this.index = index;
     }
}
