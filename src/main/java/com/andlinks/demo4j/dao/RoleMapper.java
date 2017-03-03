package com.andlinks.demo4j.dao;

import com.andlinks.demo4j.dao.sqlprovider.RoleSqlProvider;
import com.andlinks.demo4j.model.PermissionDO;
import com.andlinks.demo4j.model.RoleDO;
import com.andlinks.demo4j.model.UserDO;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by 王凯斌 on 2017/3/2.
 * 角色mapper类
 */
@Mapper
public interface RoleMapper {

    @SelectProvider(type = RoleSqlProvider.class, method = "getById")
    @Results(id="roleBase", value= {
            @Result(property = "id", column = "id"),
            @Result(property = "uuid", column = "uuid"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "modifyTime", column = "modify_time"),
            @Result(property = "version", column = "version"),
            @Result(property = "deleted", column = "is_deleted"),
            @Result(property = "roleName", column = "role_name"),
            @Result(property = "users", javaType = List.class, column = "uuid",
                    many = @Many(select = "listUsers")),
            @Result(property = "permissions", javaType = List.class, column = "uuid",
                    many = @Many(select = "listPermissions"))
    })
    RoleDO getById(@Param("id")Long id);

    @SelectProvider(type = RoleSqlProvider.class, method = "getByUuid")
    @ResultMap(value = "roleBase")
    RoleDO getByUuid(@Param("uuid")String uuid);

    @SelectProvider(type = RoleSqlProvider.class, method = "list")
    @ResultMap(value = "roleBase")
    List<RoleDO> list();

    @InsertProvider(type = RoleSqlProvider.class, method = "save")
    void save(RoleDO roleDO);

    @UpdateProvider(type = RoleSqlProvider.class, method = "update")
    void update(RoleDO roleDO);

    @DeleteProvider(type = RoleSqlProvider.class, method = "removeUsers")
    void removeUsers(@Param("uuid")String uuid);

    @InsertProvider(type = RoleSqlProvider.class, method = "insertUsers")
    void insertUsers(@Param("roleUuid")String roleUuid, @Param("userUuids")String[] userUuids);

    @DeleteProvider(type = RoleSqlProvider.class, method = "removePermissions")
    void removePermissions(@Param("uuid")String uuid);

    @InsertProvider(type = RoleSqlProvider.class, method = "insertPermissions")
    void insertPermissions(@Param("roleUuid")String roleUuid, @Param("permissionUuids")String[] permissionUuids);

    @UpdateProvider(type = RoleSqlProvider.class, method = "remove")
    void remove(@Param("uuid")String uuid);

    @SelectProvider(type = RoleSqlProvider.class, method = "listUsers")
    @Results(id="userLite", value = {
            @Result(property="id", column="id"),
            @Result(property="uuid", column="uuid"),
            @Result(property="userName", column="user_name"),
            @Result(property="password", column="password"),
    })
    List<UserDO> listUsers(String uuid);

    @SelectProvider(type = RoleSqlProvider.class, method = "listPermissions")
    @Results(id="permissionLite", value = {
            @Result(property="id", column="id"),
            @Result(property="uuid", column="uuid"),
            @Result(property="permissionName", column="permission_name"),
    })
    List<PermissionDO> listPermissions(String uuid);
}
