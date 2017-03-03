package com.andlinks.demo4j.service;

import com.andlinks.demo4j.model.UserDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by 王凯斌 on 2017/3/2.
 * User的service
 */
public interface UserService {

    /**
     * 根据id查询
     * @param id
     * @return
     */
    UserDO getById(Long id);

    /**
     * 根据uuid查询
     * @param uuid
     * @return
     */
    UserDO getByUuid(String uuid);

    /**
     * 根据用户名查询
     * @param userName
     * @return
     */
    UserDO getByUserName(String userName);

    /**
     * 获得全部user
     * @return
     */
    List<UserDO> list();

    /**
     * 保存
     * @param user
     * @return uuid
     */
    String save(UserDO user);

    /**
     * 修改
     * @param user
     */
    void update(UserDO user);

    /**
     * 修改用户的角色
     * @param userUuid
     * @param roleUuids
     */
    void updateRoles(String userUuid, String[] roleUuids);

    /**
     * 删除
     * @param uuid
     */
    void remove(String uuid);

}
