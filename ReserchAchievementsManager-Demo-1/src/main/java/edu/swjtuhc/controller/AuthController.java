package edu.swjtuhc.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import edu.swjtuhc.model.SysUser;
import edu.swjtuhc.service.AuthService;
import edu.swjtuhc.utils.JwtAuthenticationRequest;
import edu.swjtuhc.utils.JwtAuthenticationResponse;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private AuthService authService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(
            @RequestBody JwtAuthenticationRequest authenticationRequest) throws AuthenticationException{
        final String token = authService.login(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        // Return the token
        return ResponseEntity.ok(new JwtAuthenticationResponse(token));
    }

    @RequestMapping(value = "/refresh", method = RequestMethod.GET)
    public ResponseEntity<?> refreshAndGetAuthenticationToken(
            HttpServletRequest request) throws AuthenticationException{
        String token = request.getHeader(tokenHeader);
        String refreshedToken = authService.refresh(token);
        if(refreshedToken == null) {
            return ResponseEntity.badRequest().body(null);
        } else {
            return ResponseEntity.ok(new JwtAuthenticationResponse(refreshedToken));
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Integer register(@RequestBody SysUser addedUser) throws AuthenticationException{
        addedUser.setId(authService.getNextId());
    	return authService.register(addedUser);
    }
    
    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public void logout(HttpServletRequest request) throws AuthenticationException{
        
    	authService.logout();
    }
}
