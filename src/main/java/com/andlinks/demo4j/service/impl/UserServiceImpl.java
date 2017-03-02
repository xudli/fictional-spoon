package com.andlinks.demo4j.service.impl;

import com.andlinks.demo4j.dao.UserMapper;
import com.andlinks.demo4j.model.UserDO;
import com.andlinks.demo4j.service.UserService;
import com.andlinks.demo4j.util.UuidUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 王凯斌 on 2017/3/2.
 * User的serviceImlp
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDO getById(Long id) {
        return userMapper.getById(id);
    }

    @Override
    public UserDO getByUuid(String uuid) {
        return userMapper.getByUuid(uuid);
    }

    @Override
    public List<UserDO> list() {
        return userMapper.list();
    }

    @Override
    public String save(UserDO user) {

        String uuid = UuidUtils.getUUID(UserDO.class);
        user.setUuid(uuid);
        userMapper.save(user);
        return uuid;
    }

    @Override
    public void update(UserDO user) {
        userMapper.update(user);
    }

    @Override
    public void updateRoles(String userUuid, String[] roleUuids) {
        userMapper.updateRoles(userUuid,roleUuids);
    }

    @Override
    public void remove(String uuid) {
        userMapper.remove(uuid);
    }

}
