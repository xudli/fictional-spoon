package com.andlinks.demo4j.service;

import com.andlinks.demo4j.model.RoleDO;

import java.util.List;

/**
 * Created by 王凯斌 on 2017/3/2.
 * Role的service
 */
public interface RoleService {

    /**
     * 根据id查询
     * @param id
     * @return
     */
    RoleDO getById(Long id);

    /**
     * 根据uuid查询
     * @param uuid
     * @return
     */
    RoleDO getByUuid(String uuid);

    /**
     * 获得全部role
     * @return
     */
    List<RoleDO> list();


    /**
     * 保存
     * @param roleDO
     * @return uuid
     */
    String save(RoleDO roleDO);

    /**
     * 修改
     * @param roleDO
     */
    void update(RoleDO roleDO);

    /**
     * 修改角色的权限
     * @param roleUuid
     * @param permissionUuids
     */
    void updatePermission(String roleUuid, String[] permissionUuids);


    /**
     * 删除
     * @param uuid
     */
    void remove(String uuid);

}
