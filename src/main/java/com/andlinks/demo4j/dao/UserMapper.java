package com.andlinks.demo4j.dao;

import com.andlinks.demo4j.dao.sqlprovider.RoleSqlProvider;
import com.andlinks.demo4j.dao.sqlprovider.UserSqlProvider;
import com.andlinks.demo4j.model.PermissionDO;
import com.andlinks.demo4j.model.RoleDO;
import com.andlinks.demo4j.model.UserDO;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Set;

/**
 * Created by 王凯斌 on 2017/3/1.
 * 用户mapper类
 */
@Mapper
public interface UserMapper {

    @SelectProvider(type = UserSqlProvider.class, method = "getById")
    @Results(id="userBase", value = {
            @Result(property="id", column="id"),
            @Result(property="uuid", column="uuid"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "modifyTime", column = "modify_time"),
            @Result(property = "version", column = "version"),
            @Result(property = "deleted", column = "is_deleted"),
            @Result(property="userName", column="user_name"),
            @Result(property="password", column="password"),
            @Result(property="roles", javaType=List.class, column="uuid",
                    many=@Many(select="listRoles")),
            @Result(property="permissionDOs", javaType=Set.class, column="uuid",
                    many=@Many(select="listPermissions"))
    })
    UserDO getById(@Param("id")Long id);

    @SelectProvider(type = UserSqlProvider.class, method = "getByUuid")
    @ResultMap(value = "userBase")
    UserDO getByUuid(@Param("uuid")String uuid);

    @SelectProvider(type = UserSqlProvider.class, method = "getByUserName")
    @ResultMap(value = "userBase")
    UserDO getByUserName(@Param("userName")String userName);

    @SelectProvider(type = UserSqlProvider.class, method = "list")
    @ResultMap(value = "userBase")
    List<UserDO> list();

    @Insert("insert into user(user_name, password, create_time, modify_time, version, is_deleted, uuid) " +
            "values( #{userName}, #{password}, now(), now(), 0, 0, #{uuid})")
    void save(UserDO userDO);

    @UpdateProvider(type = UserSqlProvider.class,method = "update")
    void update(UserDO user);

    @UpdateProvider(type = UserSqlProvider.class,method = "updateWithoutPassword")
    void updateWithoutPassword(UserDO user);

    @DeleteProvider(type = UserSqlProvider.class, method = "removeRoles")
    void removeRoles(@Param("uuid")String uuid);

    @InsertProvider(type = UserSqlProvider.class,method = "insertRoles")
    void insertRoles(@Param("userUuid")String userUuid, @Param("roleUuids")String[] roleUuids);

    @UpdateProvider(type = UserSqlProvider.class,method = "remove")
    void remove(@Param("uuid")String uuid);

    @SelectProvider(type = UserSqlProvider.class, method = "listRoles")
    @Results(id="roleLite", value= {
            @Result(property = "id", column = "id"),
            @Result(property = "uuid", column = "uuid"),
            @Result(property = "roleName", column = "role_name"),
    })
    List<RoleDO> listRoles(String uuid);

    @SelectProvider(type = UserSqlProvider.class, method = "listPermissions")
    @Results(value = {
            @Result(property="id", column="id"),
            @Result(property="uuid", column="uuid"),
            @Result(property="permissionName", column="permission_name"),
    })
    Set<PermissionDO> listPermissions(String uuid);
}



