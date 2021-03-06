package edu.swjtuhc.service;

import java.util.List;

import edu.swjtuhc.model.SysUser;
import edu.swjtuhc.model.UserProfile;
import net.sf.json.JSONArray;

public interface UserService {
	List<SysUser> getUserList();
	UserProfile getUserProfileByAccount(String account);
	JSONArray getDepartmentList();
	Integer updateUserProfile(UserProfile userProfile);
	Integer changePassword(String account,String oldPassword,String newPassword);
}
