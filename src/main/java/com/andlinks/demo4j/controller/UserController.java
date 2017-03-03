package com.andlinks.demo4j.controller;

import com.andlinks.demo4j.dao.UserMapper;
import com.andlinks.demo4j.model.RoleDO;
import com.andlinks.demo4j.model.UserDO;
import com.andlinks.demo4j.service.RoleService;
import com.andlinks.demo4j.service.UserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 王凯斌 on 2017/3/1.
 * 用户相关controller
 */
@RestController
@RequestMapping("/test")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @RequestMapping("/delete")
    public String delete(Long id){

        UserDO user = userService.getById(id);
        userService.remove(user.getUuid());
        return "success";
    }

    @RequiresPermissions("1")
    @RequestMapping("/add")
    public UserDO add(String userName, String password){

        UserDO user = new UserDO();
        user.setUserName(userName);
        user.setPassword(password);
        String uuid = userService.save(user);
        return userService.getByUuid(uuid);
    }

    @RequestMapping("/find")
    public UserDO find(String userName){

        return userService.getByUserName(userName);
    }

    @RequestMapping("/addRole")
    public RoleDO addRole(String roleName ){

        RoleDO role = new RoleDO();
        role.setRoleName(roleName);
        String uuid = roleService.save(role);
        return roleService.getByUuid(uuid);
    }
}
