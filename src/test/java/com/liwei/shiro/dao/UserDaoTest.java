package com.liwei.shiro.dao;

import com.liwei.shiro.model.Resource;
import com.liwei.shiro.model.Role;
import com.liwei.shiro.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Liwei on 2016/9/18.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-dao.xml"})
public class UserDaoTest {

    private static final Logger logger = LoggerFactory.getLogger(UserDaoTest.class);

    @Autowired
    private UserDao userDao;

    @Test
    public void testAdd() throws Exception {
        User user = new User();
        user.setUsername("zhouguang");
        user.setPassword("666666");
        user.setNickname("周光");
        user.setStatus(1);
        Integer insertNum = userDao.add(user);
        logger.debug(" insertNum => " + insertNum);
        logger.debug("返回自增长的主键：" + user.getId());
    }

    @Test
    public void testListUser() throws Exception {
        List<User> users = userDao.listUser();
        for(User user:users){
            logger.debug(user.toString());
        }
    }

    @Test
    public void testLoadByUserName() throws Exception {
        User user = userDao.loadByUserName("liwei");
        logger.debug(user.toString());

    }

    /**
     * 根据角色 id 查询是该角色的所有用户
     * @throws Exception
     */
    @Test
    public void testListByRole() throws Exception {
        List<User> users = userDao.listByRole(2);
        for(User user:users){
            logger.debug(user.toString());
        }
    }

    @Test
    public void testListAllResources() throws Exception {
        Integer uid = 1;
        List<Resource> resources = userDao.listAllResources(uid);
        for(Resource resource:resources){
            logger.debug(resource.toString());
        }
    }

    @Test
    public void testListRoleSnByUser() throws Exception {
        List<String> sns = userDao.listRoleSnByUser(1);
        logger.debug(sns.toString());
    }

    @Test
    public void testListUserRole() throws Exception {
        List<Role> roles = userDao.listUserRole(1);
        for(Role role:roles){
            System.out.println(role.toString());
        }
    }
}