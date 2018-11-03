package edu.swjtuhc.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import edu.swjtuhc.model.SysUser;
import edu.swjtuhc.model.UserInfo;
import edu.swjtuhc.model.UserProfile;

@Mapper
public interface UserMapper {
	SysUser getUserByAccount(String account);
	List<UserInfo> getUserList();
	Integer createUser(SysUser sysUser);	
	Integer changePassword(String account, String password);
	Integer updateUser(SysUser sysUser);
	Integer deleteUser(Long uId);
}
