package com.liwei.shiro.permission;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.util.AntPathMatcher;
import org.apache.shiro.util.PatternMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Liwei on 2016/9/19.
 */
public class UrlPermission implements Permission {

    private static final Logger logger = LoggerFactory.getLogger(UrlPermission.class);

    private String url;

    public UrlPermission(String url){
        this.url = url;
    }

    @Override
    public boolean implies(Permission permission) {
        if(!(permission instanceof UrlPermission)){
            return false;
        }
        //
        UrlPermission urlPermission = (UrlPermission)permission;
        PatternMatcher patternMatcher = new AntPathMatcher();

        System.out.println("this.url => " +this.url);
        System.out.println("urlPermission.url => " + urlPermission.url);
        boolean matches = patternMatcher.matches(this.url,urlPermission.url);
        System.out.println("matches => " + matches);
        return matches;
    }
}
