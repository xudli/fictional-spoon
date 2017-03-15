package com.andlinks.demo4j.dao;

import java.util.List;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import com.andlinks.demo4j.dao.sqlprovider.PermissionSqlProvider;
import com.andlinks.demo4j.model.PermissionDO;
import com.andlinks.demo4j.model.RoleDO;

/**
 * Created by 王凯斌 on 2017/3/2. 权限mapper类
 */
@Mapper
public interface PermissionMapper{

	@SelectProvider(type = PermissionSqlProvider.class, method = "getById")
	@Results(id = "permissionBase", value = { @Result(property = "id", column = "id"),
			@Result(property = "uuid", column = "uuid"), @Result(property = "createTime", column = "create_time"),
			@Result(property = "modifyTime", column = "modify_time"), @Result(property = "version", column = "version"),
			@Result(property = "deleted", column = "is_deleted"),
			@Result(property = "permissionName", column = "permission_name"),
			@Result(property = "permissionNameCN", column = "permission_name_cn"),
			@Result(property = "roles", javaType = List.class, column = "uuid", many = @Many(select = "listRoles")) })
	PermissionDO getById(@Param("id") Long id);

	@SelectProvider(type = PermissionSqlProvider.class, method = "getByUuid")
	@ResultMap(value = "permissionBase")
	PermissionDO getByUuid(@Param("uuid") String uuid);

	@SelectProvider(type = PermissionSqlProvider.class, method = "list")
	@ResultMap(value = "permissionBase")
	List<PermissionDO> list();

	@InsertProvider(type = PermissionSqlProvider.class, method = "save")
	void save(PermissionDO permissionDO);

	@UpdateProvider(type = PermissionSqlProvider.class, method = "update")
	void update(PermissionDO permissionDO);

	@DeleteProvider(type = PermissionSqlProvider.class, method = "removeRoles")
	void removeRoles(@Param("uuid") String uuid);

	@InsertProvider(type = PermissionSqlProvider.class, method = "insertRoles")
	void insertRoles(@Param("permissionUuid") String permissionUuid, @Param("roleUuids") String[] roleUuids);

	@UpdateProvider(type = PermissionSqlProvider.class, method = "remove")
	void remove(@Param("uuid") String uuid);

	@SelectProvider(type = PermissionSqlProvider.class, method = "listRoles")
	@Results(id = "roleLite", value = { @Result(property = "id", column = "id"),
			@Result(property = "uuid", column = "uuid"), @Result(property = "roleName", column = "role_name"), })
	List<RoleDO> listRoles(String uuid);

}
