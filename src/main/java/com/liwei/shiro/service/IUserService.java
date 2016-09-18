package com.liwei.shiro.service;

import com.liwei.shiro.model.Resource;
import com.liwei.shiro.model.Role;
import com.liwei.shiro.model.User;

import java.util.List;

/**
 * Created by Liwei on 2016/9/18.
 */
public interface IUserService {

    /**
     * 添加单个用户
     * @param user
     */
    Integer add(User user);

    /**
     * 批量添加用户角色关联表数据
     * @param user
     * @param rids
     */
    void add(User user,List<Integer> rids);

    void delete(int id);

    void update(User user,List<Integer> rids);

    Integer update(User user);

    User load(int id);

    User loadByUsername(String username);

    User login(String username,String password);

    List<User> list();

    List<User> listByRole(int id);

    List<Resource> listAllResource(int uid);

    List<String> listRoleSnByUser(int uid);

    List<Role> listUserRole(int uid);

}
