package com.liwei.shiro.dao;

import com.liwei.shiro.model.Resource;
import com.liwei.shiro.model.Role;
import com.liwei.shiro.model.RoleResource;
import com.liwei.shiro.model.UserRole;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Liwei on 2016/9/18.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-dao.xml"})
public class RoleDaoTest {

    private final static Logger logger = LoggerFactory.getLogger(RoleDaoTest.class);


    @Autowired
    private RoleDao roleDao;

    @Test
    public void testListRole() throws Exception {
        List<Role> roles = roleDao.listRole();
        for(Role role:roles){
            logger.debug(role.toString());
        }
    }

    @Test
    public void testLoadUserRole() throws Exception {
        UserRole userRole = roleDao.loadUserRole(1,1);
        logger.debug(userRole.toString());
    }

    @Test
    public void testAddUserRole() throws Exception {
        Integer insertNum = roleDao.addUserRole(1,8);
        logger.debug("insertNum => " + insertNum);
    }

    @Test
    public void testDeleteUserRole() throws Exception {
        Integer deleteNum = roleDao.deleteUserRole(1,8);
        logger.debug("deleteNum => " + deleteNum);
    }

    @Test
    public void testDeleteUserRoles() throws Exception {
        Integer deleteNum = roleDao.deleteUserRoles(1);
        logger.debug("deleteNum => " + deleteNum);

    }

    @Test
    public void testListRoleResource() throws Exception {
        List<Resource> resources = roleDao.listRoleResource(1);
        for(Resource resource:resources){
            logger.debug(resource.toString());
        }
    }

    @Test
    public void testAddRoleResource() throws Exception {
        Integer insertNum = roleDao.addRoleResource(1,1);
        logger.debug("insertNum => " + insertNum);
    }

    @Test
    public void testDeleteRoleResource() throws Exception {
        Integer deleteNum = roleDao.deleteRoleResource(1,1);
        logger.debug("deleteNum => " + deleteNum);
    }

    @Test
    public void testLoadResourceRole() throws Exception {
        RoleResource roleResource = roleDao.loadResourceRole(1,1);
        logger.debug(roleResource.toString());
    }

    @Test
    public void testAddUserRoles(){
        Integer userId = 19;
        List<Integer> roleIds = new ArrayList<>();
        roleIds.add(8);
        roleIds.add(9);
        roleIds.add(10);
        Integer insertNum = roleDao.addUserRoles(userId,roleIds);
        logger.debug("insertNum => " + insertNum);
    }
}