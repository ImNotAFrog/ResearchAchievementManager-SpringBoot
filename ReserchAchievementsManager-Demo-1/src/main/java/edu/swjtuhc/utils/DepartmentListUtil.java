package edu.swjtuhc.utils;

import java.util.Arrays;
import java.util.List;

import edu.swjtuhc.enums.Department;
import edu.swjtuhc.enums.SubDepartment;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public class DepartmentListUtil {
	static {
		DepartmentListUtil.departmentList=DepartmentListUtil.getDepartmentList();
	}
	public static JSONArray departmentList;

	public static JSONArray getDepartmentList() {

		Department[] dep = Department.values();
		StringBuffer departmentList = new StringBuffer();
		departmentList.append("[");
		for (int i = 0; i < dep.length; i++) {
			StringBuffer department = new StringBuffer();
			department.append("{");
			department.append("index:'");			
			department.append(dep[i].getIndex());
			department.append("',");
			department.append("name:'");			
			department.append(dep[i].getName());
			department.append("'");
			SubDepartment[] sb = DepartmentListUtil.getSubDepartmentByDepartment(dep[i]);
			if(sb!=null) {
				department.append(",");
				department.append("subDeps:");	
				StringBuffer ss = new StringBuffer();
				ss.append("[");
				for (int j = 0; j < sb.length; j++) {
					ss.append("{");
					ss.append("index:'");
					ss.append(sb[j].getIndex());
					ss.append("',");
					ss.append("name:'");
					ss.append(sb[j].getName());
					ss.append("'}");
					if(j!=sb.length-1) {
						ss.append(",");
					}
				}
				ss.append("]");
				department.append(ss);
			}
			department.append("}");	
			departmentList.append(department);
			if(i!=dep.length-1) {
				departmentList.append(",");
			}
		}
		departmentList.append("]");
		return JSONArray.fromObject(departmentList.toString());
	}
	public static SubDepartment[] getSubDepartmentByDepartment(Department dep) {
		return dep.getSubDeps();
	}

}
