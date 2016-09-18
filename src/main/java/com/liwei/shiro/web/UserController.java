package com.liwei.shiro.web;

import com.liwei.shiro.model.User;
import com.liwei.shiro.service.IRoleService;
import com.liwei.shiro.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Liwei on 2016/9/18.
 */
@Controller
@RequestMapping(value = "/admin/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String list(Model model){
        model.addAttribute("list",userService.list());
        return "user/list";
    }

    /**
     * 跳转到添加用户的页面
     * @param model
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String add(Model model){
        logger.debug("跳转到添加用户的页面");
        // // TODO: 2016/9/18 为什么？
        // 这里使用了 Spring 的表单，进行初始化和表单数据回显
        model.addAttribute("user",new User());
        model.addAttribute("roles",roleService.list());
        return "user/add";
    }

    /**
     * 添加用户保存的方法
     * @param model
     * @param user
     * @param request
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public String add(Model model, User user, HttpServletRequest request){
        logger.debug("添加用户 post 方法");
        logger.debug(user.toString());
        List<Integer> roleIdList = new ArrayList<>();
        String[] roldIds = request.getParameterValues("roldId");
        for(String roleId:roldIds){
            roleIdList.add(Integer.parseInt(roleId));
        }
        userService.add(user,roleIdList);
        // 重定向到本 Controller 的 list 方法（Get 方式）
        return "redirect:list";
    }

    @ResponseBody
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Map<String,Object> update(User user){
        Integer updateNum = userService.update(user);
        Map<String,Object> result = new HashMap<>();
        if(updateNum > 0){
            result.put("success",true);
        }else {
            result.put("success",false);
            result.put("errorInfo","更新状态失败");
        }
        return result;
    }

}
