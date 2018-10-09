package edu.swjtuhc.handler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

@MappedTypes(value = List.class)
public class StringListHandler  implements TypeHandler<List> {

	@Override
	public void setParameter(PreparedStatement ps, int i, List parameter, JdbcType jdbcType) throws SQLException {
		// TODO Auto-generated method stub
		if(parameter!=null){	
			StringBuffer sb = new StringBuffer();
			for(int j=0;j<parameter.size();j++) {
				sb.append(parameter.get(j).toString());
				if(j!=parameter.size()-1) {
					sb.append("--;");
				}
			}
			ps.setString(i, sb.toString());
		}
	}

	@Override
	public List<String> getResult(ResultSet rs, String columnName) throws SQLException {
		// TODO Auto-generated method stub
		String[] s = null;
		String str = rs.getString(columnName);
		if(str!=null&&str.length()!=0) {
			s = str.split("--;");	
		}		
		return Arrays.asList(s);
	}

	@Override
	public List<String> getResult(ResultSet rs, int columnIndex) throws SQLException {
		// TODO Auto-generated method stub
		String[] s = null;
		String str = rs.getString(columnIndex);
		if(str!=null&&str.length()!=0) {
			s = str.split("--;");	
		}
		return Arrays.asList(s);
	}

	@Override
	public List<String> getResult(CallableStatement cs, int columnIndex) throws SQLException {
		// TODO Auto-generated method stub
		String[] s = null;
		String str = cs.getString(columnIndex);
		if(str!=null&&str.length()!=0) {
			s = str.split("--;");	
		}
		return Arrays.asList(s);
	}

}
