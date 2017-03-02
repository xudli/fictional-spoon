package com.andlinks.demo4j.controller;

import com.andlinks.demo4j.mapper.UserMapper;
import com.andlinks.demo4j.model.UserDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by 王凯斌 on 2017/3/1.
 * 用户相关controller
 */
@RestController
@RequestMapping("/test")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/test")
    public List<UserDO> test(Long id){

        return userMapper.list();
    }
}
