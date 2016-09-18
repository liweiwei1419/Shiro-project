package com.liwei.shiro.dao;

import com.liwei.shiro.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by Liwei on 2016/9/18.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-dao.xml"})
public class UserDaoTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void testAdd() throws Exception {
        User user = new User();
        user.setUsername("zhouguang");
        user.setPassword("666666");
        user.setNickname("周光");
        Integer insertNum = userDao.add(user);
        System.out.println(" insertNum => " + insertNum);
    }

    @Test
    public void testListUser() throws Exception {

    }

    @Test
    public void testLoadByUserName() throws Exception {

    }

    @Test
    public void testListByRole() throws Exception {

    }

    @Test
    public void testListAllResources() throws Exception {

    }

    @Test
    public void testListRoleSnByUser() throws Exception {

    }

    @Test
    public void testListUserRole() throws Exception {

    }
}