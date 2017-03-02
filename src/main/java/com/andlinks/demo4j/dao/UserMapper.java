package com.andlinks.demo4j.dao;

import com.andlinks.demo4j.dao.sqlprovider.UserSqlProvider;
import com.andlinks.demo4j.model.RoleDO;
import com.andlinks.demo4j.model.UserDO;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by 王凯斌 on 2017/3/1.
 * 用户mapper类
 */
@Mapper
public interface UserMapper {

    @Select("select user.id, user.uuid, user.user_name, user.password from user where user.id = #{id}")
    @Results(id="userBase", value = {
            @Result(property="id", column="id"),
            @Result(property="uuid", column="uuid"),
            @Result(property="userName", column="user_name"),
            @Result(property="password", column="password"),
            @Result(property="roles", javaType=List.class, column="uuid",
                    many=@Many(select="listRoles"))
    })
    UserDO getById(@Param("id")Long id);

    @Select("select user.id, user.uuid, user.user_name, user.password from user where user.uuid = #{uuid}")
    @ResultMap(value = "userBase")
    UserDO getByUuid(@Param("uuid")String uuid);

    @Select("select user.id, user.uuid, user.user_name, user.password from user where is_deleted = 0")
    @ResultMap(value = "userBase")
    List<UserDO> list();

    @Insert("insert into user(user_name, password, create_time, modify_time, version, is_deleted, uuid) " +
            "values( #{userName}, #{password}, now(), now(), 0, 0, #{uuid})")
    void save(UserDO userDO);

    @Update("update user set user_name=#{userName},password=#{password},modify_time=now(),version=version+1 " +
            "where user.uuid = #{uuid}")
    void update(UserDO user);

    @InsertProvider(type = UserSqlProvider.class,method = "updateRoles")
    void updateRoles(@Param("userUuid")String userUuid, @Param("roleUuids")String[] roleUuids);

    @UpdateProvider(type = UserSqlProvider.class,method = "remove")
    void remove(@Param("uuid")String uuid);

    @Select("select r.id from role r left join user_role u on r.uuid = u.role_uuid where u.user_uuid = #{uuid}")
    List<RoleDO> listRoles(String uuid);
}



