package com.andlinks.demo4j.model;

import java.util.List;

/**
 * Created by 王凯斌 on 2017/3/1. 角色实体类
 */
public class RoleDO extends BaseDO {

	private static final long serialVersionUID = -5818603968227961655L;

	private String roleName;

	private List<UserDO> users;

	private List<PermissionDO> permissions;

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public List<UserDO> getUsers() {
		return users;
	}

	public void setUsers(List<UserDO> users) {
		this.users = users;
	}

	public List<PermissionDO> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<PermissionDO> permissions) {
		this.permissions = permissions;
	}
}
