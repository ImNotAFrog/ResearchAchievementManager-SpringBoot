package edu.swjtuhc.enums;

import java.util.Arrays;
import java.util.List;

import edu.swjtuhc.enums.SubDepartment;

public enum SchoolAffairsDep implements SubDepartment{
	LEADER("部领导","LEADER"),OFFICE("校务部办公室","OFFICE"),POLICE_DIVISION("警务处","POLICE_DIVISION")
	,FINANCE("财务处","FINANCE"),MANAGEMENT("管理处","MANAGEMENT"),BARRACKS("营房处","BARRACKS")
	,SUPPLY("供给装备处","SUPPLY"),HEALH_TEAM("卫生队","HEALH_TEAM"),TEACHING_SECURE("教学保障大队","TEACHING_SECURE")
	,CAR_SQUADRON("汽车中队","CAR_SQUADRON"),POLICE_SQUADRON("警勤中队","POLICE_SQUADRON"),TEACHING_SQUADRON("示教中队","TEACHING_SQUADRON");
	private String name;
	private String index;
	private SchoolAffairsDep(String name, String index) {
		this.name=name;
		this.index =index;
		// TODO Auto-generated constructor stub
	}
	
	 public static String getName(String index) {
         for (SchoolAffairsDep d : SchoolAffairsDep.values()) {
             if (d.getIndex() == index) {
                 return d.name;
             }
         }
         return null;
     }
	 
	 public static String getIndex(String name) {
         for (SchoolAffairsDep d : SchoolAffairsDep.values()) {
             if (d.getName().equals(name)) {
                 return d.index;
             }
         }
         return null;
     }
	 public static List<SchoolAffairsDep> getList(){
		 List<SchoolAffairsDep> l = Arrays.asList(SchoolAffairsDep.values());
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
