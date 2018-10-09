package edu.swjtuhc.enums;

import java.util.Arrays;
import java.util.List;
//部领导
//学员管理部办公室
//学员一大队
//学员二大队
//学员三大队
//学员四大队
//学员五大队
//学员六大队

import edu.swjtuhc.enums.SubDepartment;

public enum ManagementDep implements SubDepartment{
	LEADER("部领导","LEADER"),OFFICE("学员管理部办公室","OFFICE"),BRIGADE_1("学员一大队","BRIGADE_1")
	,BRIGADE_2("学员二大队","BRIGADE_2"),BRIGADE_3("学员三大队","BRIGADE_2"),BRIGADE_4("学员四大队","BRIGADE_2")
	,BRIGADE_5("学员五大队","BRIGADE_2"),BRIGADE_6("学员六大队","BRIGADE_2");
	private String name;
	private String index;
	private ManagementDep(String name, String index) {
		this.name=name;
		this.index =index;
		// TODO Auto-generated constructor stub
	}
	
	 public static String getName(String index) {
         for (ManagementDep d : ManagementDep.values()) {
             if (d.getIndex() == index) {
                 return d.name;
             }
         }
         return null;
     }
	 
	 public static String getIndex(String name) {
         for (ManagementDep d : ManagementDep.values()) {
             if (d.getName().equals(name)) {
                 return d.index;
             }
         }
         return null;
     }
	 public static List<ManagementDep> getList(){
		 List<ManagementDep> l = Arrays.asList(ManagementDep.values());
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
