package edu.swjtuhc.enums;

import java.util.Arrays;
import java.util.List;

import edu.swjtuhc.enums.SubDepartment;

public enum PoliticalDep implements SubDepartment{
	LEADER("部领导","LEADER"),EDU_ORGANIZE("组织教育处","EDU_ORGANIZE"),PERSONNEL_SECTION("干部处","PERSONNEL_SECTION")
	,DISCIPLINE("纪检保卫处","DISCIPLINE"),PUBLICITY("宣传文化处","PUBLICITY");
	private String name;
	private String index;
	private PoliticalDep(String name, String index){
		this.name=name;
		this.index =index;
		// TODO Auto-generated constructor stub
	}
	
	 public static String getName(String index) {
         for (PoliticalDep d : PoliticalDep.values()) {
             if (d.getIndex() == index) {
                 return d.name;
             }
         }
         return null;
     }
	 
	 public static String getIndex(String name) {
         for (PoliticalDep d : PoliticalDep.values()) {
             if (d.getName().equals(name)) {
                 return d.index;
             }
         }
         return null;
     }
	 public static List<PoliticalDep> getList(){
		 List<PoliticalDep> l = Arrays.asList(PoliticalDep.values());
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
