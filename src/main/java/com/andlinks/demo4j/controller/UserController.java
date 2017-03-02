package com.andlinks.demo4j.controller;

import com.andlinks.demo4j.dao.UserMapper;
import com.andlinks.demo4j.model.UserDO;
import com.andlinks.demo4j.service.UserService;
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
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @RequestMapping("/delete")
    public UserDO test(Long id,String userName, String password){

        UserDO user = userMapper.getById(id);
        userMapper.remove(user.getUuid());
        System.out.println(user);
        System.out.println(userMapper.getById(id));
        return user;
    }

    @RequestMapping("/add")
    public UserDO add(Long id,String userName, String password){

        UserDO user = new UserDO();
        user.setUserName(userName);
        user.setPassword(password);
        String uuid = userService.save(user);
        return userMapper.getByUuid(uuid);
    }
}
