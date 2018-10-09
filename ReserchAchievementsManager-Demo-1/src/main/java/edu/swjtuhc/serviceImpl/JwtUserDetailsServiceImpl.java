package edu.swjtuhc.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import edu.swjtuhc.mapper.UserMapper;
import edu.swjtuhc.model.SysUser;
import edu.swjtuhc.utils.JwtUserFactory;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;
 
    @Override
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
        SysUser user = userMapper.getUserByAccount(account);
 
        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with account '%s'.", account));
        } else {
            return JwtUserFactory.create(user);
        }
    }
}
