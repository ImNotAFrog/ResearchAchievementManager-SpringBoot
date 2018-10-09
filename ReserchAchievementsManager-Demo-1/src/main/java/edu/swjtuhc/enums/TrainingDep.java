package edu.swjtuhc.enums;

import java.util.Arrays;
import java.util.List;

import edu.swjtuhc.enums.SubDepartment;

public enum TrainingDep implements SubDepartment{
	LEADER("部领导","LEADER"),DEANS_OFFICE("教务处","DEANS_OFFICE"),EDU_INSURANCE("教保处","EDU_INSURANCE")
	,RESEARCH("科研处","RESEARCH"),LIBRARY("图书馆","LIBRARY"),CULTURE("文化基础教研室","CULTURE")
	,LAW("法律教研室","LAW"),PHYSICAL("军事体育教研室","PHYSICAL"),BASICS("专业基础教研室","BASICS")
	,FIRE_PREVENTION("防火管理教研室","FIRE_PREVENTION"),FIRE_CONTROL_MANAGE("消防指挥教研室","FIRE_CONTROL_MANAGE"),EMERGENCYRESCUE("抢险救援教研室","EMERGENCYRESCUE")
	,POLITICAL_WORK("政治工作教研室","POLITICAL_WORK"),ELECTRICAL("电化教研室","ELECTRICAL");
	private String name;
	private String index;
	private TrainingDep(String name, String index) {
		this.name=name;
		this.index =index;
		// TODO Auto-generated constructor stub
	}
	
	 public static String getName(String index) {
         for (TrainingDep d : TrainingDep.values()) {
             if (d.getIndex() == index) {
                 return d.name;
             }
         }
         return null;
     }
	 
	 public static String getIndex(String name) {
         for (TrainingDep d : TrainingDep.values()) {
             if (d.getName().equals(name)) {
                 return d.index;
             }
         }
         return null;
     }
	 public static List<TrainingDep> getList(){
		 List<TrainingDep> l = Arrays.asList(TrainingDep.values());
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
