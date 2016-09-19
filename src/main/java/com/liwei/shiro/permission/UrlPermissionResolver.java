package com.liwei.shiro.permission;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.PermissionResolver;
import org.apache.shiro.authz.permission.WildcardPermission;

/**
 * Created by Liwei on 2016/9/19.
 */
public class UrlPermissionResolver implements PermissionResolver {

    @Override
    public Permission resolvePermission(String s) {
        if(s.startsWith("/")){
            return new UrlPermission(s);
        }
        return new WildcardPermission(s);
    }
}
