package com.liwei.shiro.service.impl;

import com.liwei.shiro.dao.RoleDao;
import com.liwei.shiro.dao.UserDao;
import com.liwei.shiro.model.Resource;
import com.liwei.shiro.model.Role;
import com.liwei.shiro.model.User;
import com.liwei.shiro.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Liwei on 2016/9/18.
 */
@Service
public class UserService implements IUserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Override
    public void add(User user) {
        // TODO: 2016/9/18 应该写加密以后的密码
        user.setPassword("password");
        userDao.add(user);
    }

    /**
     * 为单个用户设置多个角色
     * @param user
     * @param rids
     */
    @Override
    public void add(User user, List<Integer> rids) {
        this.add(user);
        // 应该返回自增长的主键 // TODO: 2016/9/18
        Integer userId = user.getId();
        for(Integer roleId:rids){
            roleDao.addUserRole(userId,roleId);
        }
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void update(User user, List<Integer> rids) {

    }

    @Override
    public void update(User user) {

    }

    @Override
    public User load(int id) {
        return null;
    }

    @Override
    public User loadByUsername(String username) {
        return null;
    }

    @Override
    public User login(String username, String password) {
        return null;
    }

    @Override
    public List<User> list() {
        return userDao.listUser();
    }

    @Override
    public List<User> listByRole(int id) {
        return null;
    }

    @Override
    public List<Resource> listAllResource(int uid) {
        return null;
    }

    @Override
    public List<String> listRoleSnByUser(int uid) {
        return null;
    }

    @Override
    public List<Role> listUserRole(int uid) {
        return null;
    }
}
