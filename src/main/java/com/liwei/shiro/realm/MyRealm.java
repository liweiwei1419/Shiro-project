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
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Liwei on 2016/9/19.
 */
public class MyRealm extends AuthorizingRealm {
    private static final Logger logger = LoggerFactory.getLogger(MyRealm.class);
    @Autowired
    private IUserService userService;

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        logger.info("--- MyRealm doGetAuthorizationInfo ---");

        // 获得经过认证的主体信息
        User user = (User)principalCollection.getPrimaryPrincipal();
        Integer userId = user.getId();
        // UserService userService = (UserService)InitServlet.getBean("userService");
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
        logger.info("--- MyRealm doGetAuthenticationInfo ---");
        String username = authenticationToken.getPrincipal().toString();
        String password = new String((char[])authenticationToken.getCredentials());
        // 以后我们使用 Spring 管理 Shiro 的时候，就不必要这样得到 UserService 了
        // userService = (IUserService) InitServlet.getBean("userService");
        // User user = userService.login(username,password);
        // 这里应该使用 load 方法，比对用户名的密码的环节应该交给 Shiro 这个框架去完成

        // 在测试调试的时候发现,这里还是应该使用 login 判断,因为登录不成功的原因有很多,
        // 可以在登录的逻辑里面抛出各种异常
        // 再到 subject.login(token) 里面去捕获对应的异常
        // 显示不同的消息到页面上
        User user = userService.login(username,password);
        if(user!=null){
            // 第 1 个参数可以传一个实体对象，然后在认证的环节可以取出
            // 第 2 个参数应该传递在数据库中“正确”的数据，然后和 token 中的数据进行匹配
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user,user.getPassword(),getName());
            // 设置盐值
            info.setCredentialsSalt(ByteSource.Util.bytes(username.getBytes()));
            return info;
        }
        return null;
    }

    @Override
    protected void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        Cache c = getAuthenticationCache();
        logger.info("清除【认证】缓存之前");
        for(Object o : c.keys()){
            logger.info( o + " , " + c.get(o));
        }
        super.clearCachedAuthenticationInfo(principals);
        logger.info("调用父类清除【认证】缓存之后");
        for(Object o : c.keys()){
            logger.info( o + " , " + c.get(o));
        }

        // 添加下面的代码清空【认证】的缓存
        User user = (User) principals.getPrimaryPrincipal();
        SimplePrincipalCollection spc = new SimplePrincipalCollection(user.getUsername(),getName());
        super.clearCachedAuthenticationInfo(spc);
        logger.info("添加了代码清除【认证】缓存之后");
        int cacheSize = c.keys().size();
        logger.info("【认证】缓存的大小:" + c.keys().size());
        if (cacheSize == 0){
            logger.info("说明【认证】缓存被清空了。");
        }
    }

    @Override
    protected void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        logger.info("清除【授权】缓存之前");
        Cache c = getAuthorizationCache();
        for(Object o : c.keys()){
            logger.info( o + " , " + c.get(o));
        }
        super.clearCachedAuthorizationInfo(principals);
        logger.info("清除【授权】缓存之后");
        int cacheSize = c.keys().size();
        logger.info("【授权】缓存的大小:" + cacheSize);

        for(Object o : c.keys()){
            logger.info( o + " , " + c.get(o));
        }
        if(cacheSize == 0){
            logger.info("说明【授权】缓存被清空了。");
        }

    }
}
