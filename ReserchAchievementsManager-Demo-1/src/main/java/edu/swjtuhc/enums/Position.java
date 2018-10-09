package edu.swjtuhc.enums;

import java.util.Arrays;
import java.util.List;

public enum Position {
	PRESIDENT("校长",1),COMMISSAR("政治委员",2),VICE_PRESIDENT("副校长",3)
	,VICE_COMMISSAR("副政治委员",4)	,MINISTER("部长",5),VICE_MINISTER("副部长",6)
	,DIVISION_CHIEF("处长",7),DEPUTY_DIVISION_CHIEF("副处长",8),DIRECTOR("主任",9)
	,DEPUTY_DIRECTOR("副主任",10),TEACHER("教员",11),BRANCH_DIRECTOR("大队长",12)
	,BRANCH_DEPUTYDIRECTOR("副大队长",13),INSTRUCTOR("教导员",14),ASSOCIATE_INSTRUCTOR("副教导员",15)
	,SERGEANT("中队长",16),VICE_SERGEANT("副中队长",17),ADVISER("参谋",18)
	,OFFICER("干事",19),PLATOON_LEADER("排长",20),ASSISTANT("助理员",21);
	private String name;
	private int index;
	private Position(String name, int index) {
		this.name=name;
		this.index =index;
		// TODO Auto-generated constructor stub
	}
	
	 public static String getName(int index) {
         for (Position p : Position.values()) {
             if (p.getIndex() == index) {
                 return p.name;
             }
         }
         return null;
     }
	 
	 public static int getIndex(String name) {
         for (Position p : Position.values()) {
             if (p.getName().equals(name)) {
                 return p.index;
             }
         }
         return -1;
     }
	 public static List<Position> getList(){
		 List<Position> l = Arrays.asList(Position.values());
		 return l;
	 }
     // get set 方法
     public String getName() {
         return name;
     }

     public void setName(String name) {
         this.name = name;
     }

     public int getIndex() {
         return index;
     }

     public void setIndex(int index) {
         this.index = index;
     }
	
}
