package com.liwei.shiro.service;

import com.liwei.shiro.model.Resource;
import com.liwei.shiro.model.Role;
import com.liwei.shiro.model.RoleResource;
import com.liwei.shiro.model.UserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Liwei on 2016/9/18.
 */
public interface IRoleService {
    /**
     * 添加单个角色对象
     * @param role
     */
    Integer add(Role role);

    /**
     * 根据角色 id 删除单个角色对象
     * @param id
     */
    Integer delete(int id);

    /**
     *
     * @param ids
     * @return
     */
    void deleteRoleAndResource(List<Integer> ids);

    /**
     * 根据 id 加载角色对象
     * @param id
     * @return
     */
    Role load(int id);

    /**
     * 查询所有角色对象的列表
     * @return
     */
    List<Role> list();

    /**
     * 更新单个角色对象
     * @param role
     */
    Integer update(Role role);

    /**
     * 查询所有角色列表
     * @return
     */
    List<Role> listRole();

    /**
     * 根据用户 id 和角色 id 加载一条用户角色关系数据
     * @param uid
     * @param roleId
     * @return
     */
    UserRole loadUserRole(int uid, int roleId);

    /**
     * 根据用户 id 和角色 id 插入一条用户角色关系数据
     * @param uid
     * @param roleId
     */
    void addUserRole(int uid,int roleId);

    /**
     * 根据用户 id 和角色 id 删除一条用户角色关系数据
     * @param uid
     * @param roleId
     */
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

    /**
     * 根据角色 id 和权限 id 增加一条用户权限关联数据
     * @param roleId
     * @param resId
     */
    void addRoleResource(int roleId,int resId);

    /**
     * 根据角色 id 和权限 id 删除一条用户权限关联数据
     * @param roleId
     * @param resId
     */
    void deleteRoleResource(int roleId,int resId);

    /**
     * 根据角色 id 和权限 id 查询一条用户权限关联数据
     * @param roleId
     * @param resId
     * @return
     */
    RoleResource loadResourceRole(int roleId, int resId);

    Integer deleteRoleAndUser(List<Integer> ids);
}
