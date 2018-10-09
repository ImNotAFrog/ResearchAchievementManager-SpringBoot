package edu.swjtuhc.serviceImpl;

import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import edu.swjtuhc.mapper.UserMapper;
import edu.swjtuhc.model.JwtUser;
import edu.swjtuhc.model.SysUser;
import edu.swjtuhc.service.AuthService;
import edu.swjtuhc.utils.IdWorker;
import edu.swjtuhc.utils.JwtTokenUtil;
import edu.swjtuhc.utils.JwtUserFactory;

@Service
public class AuthServiceImpl implements AuthService {

    private AuthenticationManager authenticationManager;
    private UserDetailsService userDetailsService;
    private JwtTokenUtil jwtTokenUtil;
    private UserMapper userMapper;
    private IdWorker idWorker;
    
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    public AuthServiceImpl(
            AuthenticationManager authenticationManager,
            UserDetailsService userDetailsService,
            JwtTokenUtil jwtTokenUtil,
            UserMapper userMapper) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userMapper = userMapper;
        this.idWorker=new IdWorker(0, 0);
    }

    @Override
    public Integer register(SysUser userToAdd) {
        final String username = userToAdd.getAccount();
        if(userMapper.getUserByAccount(username)!=null) {
            return -1;
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        final String rawPassword = userToAdd.getPassword();
        userToAdd.setPassword(encoder.encode(rawPassword));
        userToAdd.setLastPasswordResetDate(new Date());
        userToAdd.setRoles(userToAdd.getRoles());
        return userMapper.insertUser(userToAdd);
    }

    @Override
    public String login(String username, String password) {
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);
        final Authentication authentication = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
       
        
        UserDetails userDetails = null;
        final SysUser user = userMapper.getUserByAccount(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
        	userDetails = JwtUserFactory.create(user);
        }
        
        final String token = jwtTokenUtil.generateToken(userDetails);

        return token;
    }

    
    @Override
	public void logout() {
		// TODO Auto-generated method stub
    	SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);
	}

	@Override
    public String refresh(String oldToken) {
        final String token = oldToken.substring(tokenHead.length());
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);
        if (jwtTokenUtil.canTokenBeRefreshed(token, user.getLastPasswordResetDate())){
            return jwtTokenUtil.refreshToken(token);
        }
        return null;
    }

	@Override
	public Long getNextId() {
		// TODO Auto-generated method stub
		return idWorker.nextId();
	}

	@Override
	public boolean verifyPassword(String account, String password) {
		// TODO Auto-generated method stub
		UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(account, password);
	    final Authentication authentication = authenticationManager.authenticate(upToken);
	    return true;
	}
    
}

