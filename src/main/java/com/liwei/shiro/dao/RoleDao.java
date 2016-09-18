package com.liwei.shiro.dao;

import com.liwei.shiro.model.Resource;
import com.liwei.shiro.model.Role;
import com.liwei.shiro.model.RoleResource;
import com.liwei.shiro.model.UserRole;

import java.util.List;

/**
 * Created by Liwei on 2016/9/18.
 */
public interface RoleDao {

    public List<Role> listRole();

    public UserRole loadUserRole(int uid, int roleId);

    public void addUserRole(int uid,int roleId);

    public void deleteUserRole(int uid,int roleId);

    /**
     * 删除某个用户的所有角色
     * @param uid
     */
    public void deleteUserRoles(int uid);
    /**
     * 根据角色id获取可以访问的所有资源
     * @param roleId
     * @return
     */
    public List<Resource> listRoleResource(int roleId);

    public void addRoleResource(int roleId,int resId);

    public void deleteRoleResource(int roleId,int resId);

    public RoleResource loadResourceRole(int roleId, int resId);
}
