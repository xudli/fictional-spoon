package com.andlinks.demo4j.controller;

import com.andlinks.demo4j.dao.UserMapper;
import com.andlinks.demo4j.model.RoleDO;
import com.andlinks.demo4j.model.UserDO;
import com.andlinks.demo4j.service.RoleService;
import com.andlinks.demo4j.service.UserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by 王凯斌 on 2017/3/1.
 * 用户相关controller
 */
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @RequestMapping(value="/user/{uuid}",method=RequestMethod.GET)
    public UserDO get(@PathVariable String uuid){

        return userService.getByUuid(uuid);
    }

    @RequestMapping(value="/user",method = RequestMethod.GET)
    public List<UserDO> list(){

        return userService.list();
    }

    @RequestMapping(value="/user",method = RequestMethod.POST)
    public String save(UserDO user){

        return userService.save(user);
    }

    @RequestMapping(value="/user/{uuid}",method = RequestMethod.PUT)
    public String update(@PathVariable String uuid,UserDO user){

        user.setUuid(uuid);
        userService.update(user);
        return "success";
    }

    @RequestMapping(value="/user/{uuid}/roles",method = RequestMethod.PUT)
    public String updateRoles(@PathVariable String uuid,String[] roleUuids){

        userService.updateRoles(uuid,roleUuids);
        return "success";
    }

    @RequestMapping(value="/user/{uuid}",method = RequestMethod.DELETE)
    public String delete(@PathVariable String uuid){

        userService.remove(uuid);
        return "success";
    }
}
