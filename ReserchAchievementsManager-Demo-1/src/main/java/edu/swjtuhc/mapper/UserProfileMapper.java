package edu.swjtuhc.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import edu.swjtuhc.model.UserProfile;

@Mapper
public interface UserProfileMapper {
	Integer insertUserProfile(UserProfile userProfile);
	Integer updateUserProfile(UserProfile userProfile);
	UserProfile getUserProfileByAccount(String account);
}
