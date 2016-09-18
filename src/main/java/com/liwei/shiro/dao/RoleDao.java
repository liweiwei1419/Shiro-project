package com.liwei.shiro.dao;

import com.liwei.shiro.model.Resource;
import com.liwei.shiro.model.Role;
import com.liwei.shiro.model.RoleResource;
import com.liwei.shiro.model.UserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Liwei on 2016/9/18.
 */
public interface RoleDao {

    List<Role> listRole();

    UserRole loadUserRole(@Param("userId") int userId,@Param("roleId") int roleId);

    Integer addUserRole(@Param("userId") int userId,@Param("roleId") int roleId);

    Integer deleteUserRole(@Param("userId") int userId,@Param("roleId") int roleId);

    /**
     * 删除某个用户的所有角色
     * @param uid
     */
    Integer deleteUserRoles(int uid);
    /**
     * 根据角色id获取可以访问的所有资源
     * @param roleId
     * @return
     */
    List<Resource> listRoleResource(int roleId);

    Integer addRoleResource(@Param("roleId") int roleId,@Param("resourceId") int resourceId);

    Integer deleteRoleResource(@Param("roleId")int roleId,@Param("resourceId")int resorceId);

    RoleResource loadResourceRole(@Param("roleId")int roleId,@Param("resourceId") int resorceId);
}
