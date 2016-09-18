package com.liwei.shiro.service.impl;

import com.liwei.shiro.dao.RoleDao;
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
public class RoleService implements IRoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public void add(Role role) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Role load(int id) {
        return null;
    }

    @Override
    public List<Role> list() {
        return roleDao.listRole();
    }

    @Override
    public void update(Role role) {

    }

    @Override
    public List<Role> listRole() {
        return null;
    }

    @Override
    public UserRole loadUserRole(int uid, int roleId) {
        return null;
    }

    @Override
    public void addUserRole(int uid, int roleId) {

    }

    @Override
    public void deleteUserRole(int uid, int roleId) {

    }

    @Override
    public void deleteUserRoles(int uid) {

    }

    @Override
    public List<Resource> listRoleResource(int roleId) {
        return null;
    }

    @Override
    public void addRoleResource(int roleId, int resId) {

    }

    @Override
    public void deleteRoleResource(int roleId, int resId) {

    }

    @Override
    public RoleResource loadResourceRole(int roleId, int resId) {
        return null;
    }
}
