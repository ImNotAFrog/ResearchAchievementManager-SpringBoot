package edu.swjtuhc.serviceImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import edu.swjtuhc.mapper.UserMapper;
import edu.swjtuhc.mapper.UserProfileMapper;
import edu.swjtuhc.model.SysUser;
import edu.swjtuhc.model.UserInfo;
import edu.swjtuhc.model.UserProfile;
import edu.swjtuhc.service.AuthService;
import edu.swjtuhc.service.UserService;
import edu.swjtuhc.utils.DepartmentListUtil;
import edu.swjtuhc.utils.IdWorker;
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
	public List<UserInfo> getUserList() {
		// TODO Auto-generated method stub
		List<UserInfo> list = userMapper.getUserList();
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setPassword(null);
		}
		return list;
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

	@Override
	public Integer createUser(UserInfo userInfo) {
		// TODO Auto-generated method stub
		SysUser user = new SysUser();
		user.setAccount(userInfo.getAccount());
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        final String rawPassword = userInfo.getPassword();
        user.setPassword(encoder.encode(rawPassword));
		user.setLastPasswordResetDate(new Date());
		user.setRoles(userInfo.getRoles());
		user.setId(IdWorker.getInstance().nextId());
		UserProfile profile = new UserProfile();
		profile.setAccount(userInfo.getAccount());
		profile.setName(userInfo.getUsername());
		Integer i =0;
		try {
			i=createUser(user,profile);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return i;
	}

	@Override
	public Integer updateUser(UserInfo userInfo) {
		// TODO Auto-generated method stub
		
		SysUser user = userMapper.getUserByAccount(userInfo.getAccount());
        final String rawPassword = userInfo.getPassword();
		if(rawPassword!=null&&rawPassword!="") {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	        user.setPassword(encoder.encode(rawPassword));
		}		
		user.setLastPasswordResetDate(new Date());
		user.setRoles(userInfo.getRoles());
		UserProfile profile = userProfileMapper.getUserProfileByAccount(userInfo.getAccount());
		profile.setName(userInfo.getUsername());
		Integer i =0;
		try {
			i =updateUser(user,profile);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return i;
	}

	@Override
	public Integer deleteUser(Long uId) {
		// TODO Auto-generated method stub
		SysUser u = userMapper.getUserById(uId);
		if(u==null) {
			return 0;
		}
		return deleteUserAndProfile(uId,u.getAccount());
	}
	
	@Override
	public UserInfo getUserById(Long uId) {
		// TODO Auto-generated method stub	
		UserInfo userInfo = new UserInfo();
		SysUser user = userMapper.getUserById(uId);
		UserProfile profile = userProfileMapper.getUserProfileByAccount(user.getAccount());
		userInfo.setAccount(user.getAccount());
		userInfo.setRoles(user.getRoles());
		userInfo.setuId(user.getId());
		userInfo.setUsername(profile.getName());
		return userInfo;
	}
	
	
    @Transactional(propagation=Propagation.REQUIRES_NEW,isolation=Isolation.SERIALIZABLE)
    private synchronized Integer createUser(SysUser user,UserProfile profile) {
    	int i=0;
		if(userMapper.createUser(user)==1) {
			if(userProfileMapper.insertUserProfile(profile)==1) {
				i=1;
			}			
		}
		return i;
    }
    
    @Transactional(propagation=Propagation.REQUIRES_NEW,isolation=Isolation.SERIALIZABLE)
    private synchronized Integer updateUser(SysUser user,UserProfile profile) {
    	int i=0;
		if(userMapper.updateUser(user)==1) {
			if(userProfileMapper.updateUserProfile(profile)==1) {
				i=1;
			}			
		}
		return i;
    }
    @Transactional(propagation=Propagation.REQUIRES_NEW,isolation=Isolation.SERIALIZABLE)
    private synchronized Integer deleteUserAndProfile(Long uId,String account) {
    	int i=0;
		if(userMapper.deleteUser(uId)==1) {
			if(userProfileMapper.deleteUserProfile(account)==1) {
				i=1;
			}			
		}
		return i;
    }


}
