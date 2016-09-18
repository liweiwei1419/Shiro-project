package com.liwei.shiro.service;

import com.liwei.shiro.model.Resource;
import com.liwei.shiro.model.Role;
import com.liwei.shiro.model.RoleResource;
import com.liwei.shiro.model.UserRole;

import java.util.List;

/**
 * Created by Liwei on 2016/9/18.
 */
public interface IRoleService {

    void add(Role role);

    void delete(int id);

    Role load(int id);

    List<Role> list();

    void update(Role role);

    List<Role> listRole();


    UserRole loadUserRole(int uid, int roleId);

    void addUserRole(int uid,int roleId);

    void deleteUserRole(int uid,int roleId);

    /**
     * 删除某个用户的所有角色
     * @param uid
     */
    void deleteUserRoles(int uid);
    /**
     * 根据角色id获取可以访问的所有资源
     * @param roleId
     * @return
     */
    List<Resource> listRoleResource(int roleId);

    void addRoleResource(int roleId,int resId);

    void deleteRoleResource(int roleId,int resId);

    RoleResource loadResourceRole(int roleId, int resId);
}
