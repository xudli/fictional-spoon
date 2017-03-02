package com.andlinks.demo4j.service;

import com.andlinks.demo4j.model.PermissionDO;
import com.andlinks.demo4j.model.RoleDO;

import java.util.List;

/**
 * Created by 王凯斌 on 2017/3/2.
 * Permission的service
 */
public interface PermissionService {

    /**
     * 根据id查询
     * @param id
     * @return
     */
    PermissionDO getById(Long id);

    /**
     * 根据uuid查询
     * @param uuid
     * @return
     */
    PermissionDO getByUuid(String uuid);

    /**
     * 获得全部permission
     * @return
     */
    List<PermissionDO> list();


    /**
     * 保存
     * @param permissionDO
     * @return uuid
     */
    String save(PermissionDO permissionDO);

    /**
     * 修改
     * @param permissionDO
     */
    void update(PermissionDO permissionDO);


    /**
     * 删除
     * @param uuid
     */
    void remove(String uuid);

}
