package com.liwei.shiro.web;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 * Created by Liwei on 2016/9/19.
 */
public class InitServlet extends HttpServlet {
    private static WebApplicationContext wc;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        // 初始化 Spring 的工厂
        wc = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
    }

    public static WebApplicationContext getWc() {
        return wc;
    }

    public static Object getBean(String beanName){
        return wc.getBean(beanName);
    }
}
