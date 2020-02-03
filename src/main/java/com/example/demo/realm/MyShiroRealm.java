package com.example.demo.realm;

import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import com.example.demo.utils.JWTUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.HashSet;
import java.util.Set;


/**
 * Created by Oliver Seth on 2020/2/2 16:31
 */
public class MyShiroRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;

    @Override
    public void setName(String name) {
        super.setName("MyShiroRealm");
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String userName = JWTUtils.getUsername((String) principalCollection.getPrimaryPrincipal());
        User user = userService.findByUserName(userName);
        Set<String> roles = new HashSet<>();
        roles.add(user.getRoleName());
        Set<String> perms = new HashSet<>();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setRoles(roles);
        info.setStringPermissions(perms);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String token = (String) authenticationToken.getCredentials();
        String username = JWTUtils.getUsername(token);
        if (username == null) {
            throw new AuthenticationException("Token invalid");
        }
        User user = userService.findByUserName(username);
        if (user == null) {
            throw new AuthenticationException("User not exists!");
        }
//        if(user.getPassword().equals())
        return new SimpleAuthenticationInfo(token, token, getName());
    }
}
