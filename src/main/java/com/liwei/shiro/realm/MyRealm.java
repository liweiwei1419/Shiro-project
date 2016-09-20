package com.liwei.shiro.realm;

import com.liwei.shiro.model.Resource;
import com.liwei.shiro.model.User;
import com.liwei.shiro.service.IUserService;
import com.liwei.shiro.service.impl.UserService;
import com.liwei.shiro.web.InitServlet;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Liwei on 2016/9/19.
 */
public class MyRealm extends AuthorizingRealm {

    private IUserService userService;

    private static final Logger logger = LoggerFactory.getLogger(MyRealm.class);


    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        logger.debug("MyRealm doGetAuthorizationInfo");

        // 获得经过认证的主体信息
        User user = (User)principalCollection.getPrimaryPrincipal();
        Integer userId = user.getId();
        UserService userService = (UserService)InitServlet.getBean("userService");
        List<Resource> resourceList = userService.listAllResource(userId);
        List<String> roleSnList = userService.listRoleSnByUser(userId);

        List<String> resStrList = new ArrayList<>();
        for(Resource resource:resourceList){
            resStrList.add(resource.getUrl());
        }

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setRoles(new HashSet<>(roleSnList));
        info.setStringPermissions(new HashSet<>(resStrList));

        // 以上完成了动态地对用户授权
        logger.debug("role => " + roleSnList);
        logger.debug("permission => " + resStrList);

        return info;
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
        // String password = new String((char[])authenticationToken.getCredentials());
        // 以后我们使用 Spring 管理 Shiro 的时候，就不必要这样得到 UserService 了
        userService = (IUserService) InitServlet.getBean("userService");
        // User user = userService.login(username,password);
        // 这里应该使用 load 方法，比对用户名的密码的环节应该交给 Shiro 这个框架去完成
        User user = userService.loadByUsername(username);
        // 第 1 个参数可以传一个实体对象，然后在认证的环节可以取出
        // 第 2 个参数应该传递在数据库中“正确”的数据，然后和 token 中的数据进行匹配
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user,user.getPassword(),getName());
        // 设置盐值
        info.setCredentialsSalt(ByteSource.Util.bytes(username.getBytes()));
        return info;
    }
}
