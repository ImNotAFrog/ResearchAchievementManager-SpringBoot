package edu.swjtuhc.enums;

import java.util.Arrays;
import java.util.List;

public enum Department {
	LEADER("校领导","LEADER"),TRAINNING("训练部","TRAINNING"),POLITICAL("政治部","POLITICAL")
	,SCHOOL_AFFAIRS("校务部","SCHOOL_AFFAIRS"),MANAGEMENT("学员管理部","MANAGEMENT");
	private String name;
	private String index;
	private Department(String name, String index) {
		this.name=name;
		this.index =index;
		// TODO Auto-generated constructor stub
	}
	
	 public static String getName(String index) {
         for (Department d : Department.values()) {
             if (d.getIndex() == index) {
                 return d.name;
             }
         }
         return null;
     }
	 
	 public static String getIndex(String name) {
         for (Department d : Department.values()) {
             if (d.getName().equals(name)) {
                 return d.index;
             }
         }
         return null;
     }
	 public static List<Department> getAll(){
		 List<Department> l = Arrays.asList(Department.values());
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
     public SubDepartment[] getSubDeps(){
    	 SubDepartment[] l = null;
    	 switch(this){
    	 case TRAINNING:
    		 l=TrainingDep.values();
    		 break;
    	 case POLITICAL:
    		 l = PoliticalDep.values();
    		 break;
    	 case SCHOOL_AFFAIRS:
    		 l = SchoolAffairsDep.values();
    		 break;
    	 case MANAGEMENT:
    		 l = ManagementDep.values();
    		 break;
		 default:
			 break;
    	 }
		return l;    	 
     }
}
