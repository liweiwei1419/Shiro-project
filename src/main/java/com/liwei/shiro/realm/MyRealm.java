package com.liwei.shiro.realm;

import com.liwei.shiro.model.User;
import com.liwei.shiro.service.IUserService;
import com.liwei.shiro.service.impl.UserService;
import com.liwei.shiro.web.InitServlet;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * Created by Liwei on 2016/9/19.
 */
public class MyRealm extends AuthorizingRealm {

    private IUserService userService;


    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = authenticationToken.getPrincipal().toString();
        String password = new String((char[])authenticationToken.getCredentials());
        userService = (IUserService) InitServlet.getBean("userService");
        User user = userService.loadByUsername(username);
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user.getUsername(),user.getPassword(),getName());
        return info;
    }
}
