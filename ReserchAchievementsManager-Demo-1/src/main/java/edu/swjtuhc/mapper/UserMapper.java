package edu.swjtuhc.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import edu.swjtuhc.model.SysUser;

@Mapper
public interface UserMapper {
	SysUser getUserByAccount(String account);
	List<SysUser> getUserList();
	Integer insertUser(SysUser user);
	Integer changePassword(String account, String password);
}
