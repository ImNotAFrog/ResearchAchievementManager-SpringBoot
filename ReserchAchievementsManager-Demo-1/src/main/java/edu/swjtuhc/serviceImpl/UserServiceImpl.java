package edu.swjtuhc.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import edu.swjtuhc.mapper.UserMapper;
import edu.swjtuhc.mapper.UserProfileMapper;
import edu.swjtuhc.model.SysUser;
import edu.swjtuhc.model.UserProfile;
import edu.swjtuhc.service.AuthService;
import edu.swjtuhc.service.UserService;
import edu.swjtuhc.utils.DepartmentListUtil;
import net.sf.json.JSONArray;

@Service
public class UserServiceImpl implements UserService{
    
    @Autowired
    private UserMapper userMapper;
    
    @Autowired
    private UserProfileMapper userProfileMapper;
    
    @Autowired
    private AuthService authService;

	@Override
	public List<SysUser> getUserList() {
		// TODO Auto-generated method stub
		return userMapper.getUserList();
	}

	@Override
	public UserProfile getUserProfileByAccount(String account) {
		// TODO Auto-generated method stub
		return userProfileMapper.getUserProfileByAccount(account);
	}

	@Override
	public JSONArray getDepartmentList(){
		// TODO Auto-generated method stub
		return DepartmentListUtil.departmentList;
	}

	@Override
	public Integer updateUserProfile(UserProfile userProfile) {
		// TODO Auto-generated method stub
		return userProfileMapper.updateUserProfile(userProfile);
	}

	@Override
	public Integer changePassword(String account,String oldPassword,String newPassword) {
		// TODO Auto-generated method stub
		authService.verifyPassword(account, oldPassword);

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		return userMapper.changePassword(account, encoder.encode(newPassword));
	}
    
    
}
