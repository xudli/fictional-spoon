package com.andlinks.demo4j.service.impl;

import com.andlinks.demo4j.dao.PermissionMapper;
import com.andlinks.demo4j.model.PermissionDO;
import com.andlinks.demo4j.model.UserDO;
import com.andlinks.demo4j.service.PermissionService;
import com.andlinks.demo4j.util.UuidUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 王凯斌 on 2017/3/2.
 * Permission的serviceImlp
 */
@Service
public class PermissionServiceImpl implements PermissionService{

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public PermissionDO getById(Long id) {
        return permissionMapper.getById(id);
    }

    @Override
    public PermissionDO getByUuid(String uuid) {
        return permissionMapper.getByUuid(uuid);
    }

    @Override
    public List<PermissionDO> list() {
        return permissionMapper.list();
    }

    @Override
    public String save(PermissionDO permissionDO) {
        String uuid = UuidUtils.getUUID(UserDO.class);
        permissionDO.setUuid(uuid);
        permissionMapper.save(permissionDO);
        return uuid;
    }

    @Override
    public void update(PermissionDO permissionDO) {
        permissionMapper.update(permissionDO);
    }

    @Override
    public void remove(String uuid) {
        permissionMapper.remove(uuid);
    }
}
