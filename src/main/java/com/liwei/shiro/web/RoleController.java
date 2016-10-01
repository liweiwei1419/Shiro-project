package com.liwei.shiro.web;

import com.liwei.shiro.model.Resource;
import com.liwei.shiro.model.Role;
import com.liwei.shiro.model.RoleResource;
import com.liwei.shiro.service.IResourceService;
import com.liwei.shiro.service.IRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Liwei on 2016/9/19.
 */
@Controller
@RequestMapping("/admin/role")
public class RoleController {
    private static final Logger logger = LoggerFactory.getLogger(RoleController.class);
    @Autowired
    private IRoleService roleService;
    @Autowired
    private IResourceService resourceService;

    /**
     * 跳转到查询所有角色的页面
     * @return
     */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String list(Model model){
        List<Role> roleList = roleService.list();
        model.addAttribute("roleList",roleList);
        return "role/list";
    }

    /**
     * 跳转到添加角色的页面
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String add(Model model){
        // 为了表单回显的须要，要在 Model 里添加一个新对象
        model.addAttribute("role",new Role());
        return "role/add";
    }

    /**
     * 添加用户角色的后台方法
     * @param role
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public String add(Role role){
        logger.debug(role.toString());
        roleService.add(role);
        return "redirect:list";
    }

    /**
     * 跳转到更新角色的页面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/update/{id}",method = RequestMethod.GET)
    public String update(@PathVariable("id") Integer id, Model model){
        Role role = roleService.load(id);
        model.addAttribute("role",role);
        return "role/update";
    }

    /**
     * 修改角色对象的方法
     * @param role
     * @return
     */
    @RequestMapping(value = "/update/{id}",method = RequestMethod.POST)
    public String update(Role role){
        logger.debug(role.toString());
        roleService.update(role);
        return "redirect:/admin/role/list";
    }

    @RequestMapping(value = "/resources/{id}",method = RequestMethod.GET)
    public String listResources(@PathVariable("id") Integer id,Model model){
        /**
         * 查询这个角色拥有的资源集合
         */
        List<Resource> hasResourceList = roleService.listRoleResource(id);
        List<Integer> hasResourceIds = new ArrayList<>();
        for(Resource resource:hasResourceList){
            hasResourceIds.add(resource.getId());
        }

        // 查询所有资源列表
        List<Resource> resourceAllList = resourceService.listResource();
        // 查询角色对象
        Role role = roleService.load(id);

        model.addAttribute("hasResourceIds",hasResourceIds);
        model.addAttribute("resourceList",resourceAllList);
        model.addAttribute("role",role);
        return "role/resources";
    }

    /**
     * 设置用户权限
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/resource",method = RequestMethod.POST)
    public Map<String,Object> resource(RoleResource roleResource,Integer check){
        logger.debug(roleResource.toString());
        Integer roleId = roleResource.getRoleId();
        Integer resourceId = roleResource.getResourceId();
        Map<String,Object> result = new HashMap<>();
        if(check != null){
            if(check == 0){
                roleService.deleteRoleResource(roleId,resourceId);
            }
            if(check == 1){
                roleService.addRoleResource(roleId,resourceId);
            }
            result.put("success",true);
        }else {
            result.put("success",false);
            result.put("errorInfo","数据修改失败");
        }
        return result;
    }

    /**
     *
     * @param roleIds
     */
    @ResponseBody
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public Map<String,Object> deleteRole(@RequestParam("roleIds[]") List<Integer> roleIds){
        logger.debug(roleIds.toString());
        for(Integer roleId:roleIds){
            logger.debug(roleId.toString());
        }

        // 先批量删除角色,再从角色资源表中删除角色资源数据
        roleService.deleteRoleAndResource(roleIds);
        // 用户绑定到这个角色上,也应该删除
        roleService.deleteRoleAndUser(roleIds);
        Map<String,Object> result = new HashMap<>();
        result.put("success",true);
        return result;
    }
}
