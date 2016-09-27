package com.liwei.shiro;

import com.liwei.shiro.service.HelloWorld;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by liwei on 16/9/25.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-shiro.xml","classpath:spring/spring-service.xml","classpath:spring/spring-dao.xml"})
public class HelloWorldTest {

    @Autowired
    private HelloWorld helloWorld;

    @Test
    public void testHelloWorld(){
        String str = helloWorld.hello("world");
        /**
         * 第 1 个参数:测试不通过的时候显示的消息
         * 第 2 个参数:期望值
         * 第 3 个参数:实际值
         */
        Assert.assertEquals("测试不通过","world",str);
    }
}
