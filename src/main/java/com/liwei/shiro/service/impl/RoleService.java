package com.liwei.shiro.service.impl;

import com.liwei.shiro.cache.BaseCacheService;
import com.liwei.shiro.dao.RoleDao;
import com.liwei.shiro.dao.UserDao;
import com.liwei.shiro.model.Resource;
import com.liwei.shiro.model.Role;
import com.liwei.shiro.model.RoleResource;
import com.liwei.shiro.model.UserRole;
import com.liwei.shiro.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Liwei on 2016/9/18.
 */
@Service
public class RoleService extends BaseCacheService implements IRoleService {

    @Autowired
    private RoleDao roleDao;


    @Override
    public Integer add(Role role) {
        return roleDao.add(role);
    }

    @Override
    public Integer delete(int id) {
        return roleDao.delete(id);
    }

    @Override
    public Role load(int id) {
        return roleDao.load(id);
    }

    @Override
    public List<Role> list() {
        List<Role> roles = (List<Role>) super.get("allRoles");
        if(roles == null){
            roles = roleDao.listRole();
            super.put("allRoles",roles);
        }
        return roles;
    }

    @Override
    public Integer update(Role role) {
        return roleDao.update(role);
    }

    @Override
    public List<Role> listRole() {
        return roleDao.listRole();
    }

    @Override
    public UserRole loadUserRole(int uid, int roleId) {
        return roleDao.loadUserRole(uid, roleId);
    }

    @Override
    public void addUserRole(int uid, int roleId) {
        roleDao.addUserRole(uid, roleId);
    }

    @Override
    public void deleteUserRole(int uid, int roleId) {
        roleDao.deleteUserRole(uid, roleId);
    }

    @Override
    public void deleteUserRoles(int uid) {
        roleDao.deleteUserRoles(uid);
    }

    @Override
    public List<Resource> listRoleResource(int roleId) {
        return roleDao.listRoleResource(roleId);
    }

    @Override
    public void addRoleResource(int roleId, int resId) {
        roleDao.addRoleResource(roleId, resId);
    }

    @Override
    public void deleteRoleResource(int roleId, int resId) {
        roleDao.deleteRoleResource(roleId, resId);
    }

    @Override
    public RoleResource loadResourceRole(int roleId, int resId) {
        return roleDao.loadResourceRole(roleId, resId);
    }

    public RoleService() {
        super.setCacheName("roleServiceCache");
    }
}
