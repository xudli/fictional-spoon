package com.andlinks.demo4j.service.impl;

import com.andlinks.demo4j.dao.UserMapper;
import com.andlinks.demo4j.model.UserDO;
import com.andlinks.demo4j.service.UserService;
import com.andlinks.demo4j.util.UuidUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
    public UserDO getByUserName(String userName) {
        return userMapper.getByUserName(userName);
    }

    @Override
    public List<UserDO> list() {
        return userMapper.list();
    }

    @Override
    public String save(UserDO user) {

        if(getByUserName(user.getUserName())!=null){
            return null;
        }
        String uuid = UuidUtils.getUUID(UserDO.class);
        user.setUuid(uuid);
        user.setPassword(DigestUtils.md5Hex(user.getPassword()));
        userMapper.save(user);
        return uuid;
    }

    @Override
    public void update(UserDO user) {
        userMapper.update(user);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updateRoles(String userUuid, String[] roleUuids) {

        userMapper.removeRoles(userUuid);
        userMapper.insertRoles(userUuid,roleUuids);
    }

    @Override
    public void remove(String uuid) {
        userMapper.remove(uuid);
    }

}
