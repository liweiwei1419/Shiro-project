package com.liwei.shiro.dao;

import com.liwei.shiro.model.Resource;
import com.liwei.shiro.model.Role;
import com.liwei.shiro.model.User;

import java.util.List;

/**
 * Created by Liwei on 2016/9/18.
 */
public interface UserDao {

    Integer add(User user);


    List<User> listUser();

    User loadByUserName(String username);

    List<User> listByRole(Integer uid);

    List<Resource> listAllResources();

    List<String> listRoleSnByUser(Integer uid);

    List<Role> listUserRole(Integer uid);
}
