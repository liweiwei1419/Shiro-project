package com.liwei.shiro.matcher;

import org.apache.shiro.util.AntPathMatcher;
import org.apache.shiro.util.PatternMatcher;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by liwei on 16/9/20.
 */
public class PathMatcherTest {


    /**
     * 使用该工具类,可以完成对路径的匹配
     */
    @Test
    public void test01(){
        PatternMatcher patternMatcher = new AntPathMatcher();
        String str1 = "/admin/user/**";
        String str2 = "/admin/user/1";
        boolean match = patternMatcher.matches(str1,str2);
        System.out.println(match);
    }

}
