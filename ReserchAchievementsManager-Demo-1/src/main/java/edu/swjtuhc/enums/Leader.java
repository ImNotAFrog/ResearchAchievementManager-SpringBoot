package edu.swjtuhc.enums;

import java.util.Arrays;
import java.util.List;

import edu.swjtuhc.enums.SubDepartment;

public enum Leader implements SubDepartment{
	LEADER("校领导","LEADER");

	private String name;
	private String index;
	private Leader(String name, String index){
		this.name=name;
		this.index =index;
		// TODO Auto-generated constructor stub
	}
	
	 public static String getName(String index) {
         for (Leader d : Leader.values()) {
             if (d.getIndex() == index) {
                 return d.name;
             }
         }
         return null;
     }
	 
	 public static String getIndex(String name) {
         for (Leader d : Leader.values()) {
             if (d.getName().equals(name)) {
                 return d.index;
             }
         }
         return null;
     }
	 public static List<Leader> getList(){
		 List<Leader> l = Arrays.asList(Leader.values());
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
