package edu.swjtuhc.enums;

import java.util.Arrays;
import java.util.List;

public enum PositionLevel {
	
	COMMANDER("军长","COMMANDER"),DEPUTY_COMMANDER("副军长","DEPUTY_COMMANDER"),DIVISION_COMMANDER("师长","DIVISION_COMMANDER")
	,VICE_DIVISIONCOMMANDER("副师长","VICE_DIVISIONCOMMANDER"),REGIMENT_COMMANDER("团长","REGIMENT_COMMANDER"),DEPUTY_REGIMENT_COMMANDER("副团长","DEPUTY_REGIMENT_COMMANDER")
	,BATTALION_COMMANDER("营长","BATTALION_COMMANDER"),DEPUTY_BATTALION_COMMANDER("副营长","DEPUTY_BATTALION_COMMANDER"),COMPANY_COMMANDER("连长","COMPANY_COMMANDER")
	,DEPUTY_COMPANY_COMMANDER("副连长","DEPUTY_COMPANY_COMMANDER"),PLATOON_LEADER("排长","PLATOON_LEADER"),STUDENT("学员","STUDENT");
	private String name;
	private String index;
	private PositionLevel(String name, String index) {
		this.name=name;
		this.index =index;
		// TODO Auto-generated constructor stub
	}
	
	 public static String getName(String index) {
         for (PositionLevel p : PositionLevel.values()) {
             if (p.getIndex() == index) {
                 return p.name;
             }
         }
         return null;
     }
	 
	 public static String getIndex(String name) {
         for (PositionLevel p : PositionLevel.values()) {
             if (p.getName().equals(name)) {
                 return p.index;
             }
         }
         return null;
     }
	 
	 public static List<PositionLevel> getList(){
		 List<PositionLevel> l = Arrays.asList(PositionLevel.values());
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
