package edu.swjtuhc.service;

import edu.swjtuhc.model.SysUser;

public interface AuthService {
    String login(String username, String password);
    String refresh(String oldToken);
    void logout();
    Long getNextId();
    boolean verifyPassword(String account, String password);
}
