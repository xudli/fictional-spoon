package com.andlinks.demo4j.model;

import java.util.List;

/**
 * Created by 王凯斌 on 2017/3/1. 权限实体类
 */
public class PermissionDO extends BaseDO {

	private static final long serialVersionUID = 826656225461573518L;

	private String permissionName;

	private String permissionNameCN;

	private List<RoleDO> roles;

	public String getPermissionName() {
		return permissionName;
	}

	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}

	public String getPermissionNameCN() {
		return permissionNameCN;
	}

	public void setPermissionNameCN(String permissionNameCN) {
		this.permissionNameCN = permissionNameCN;
	}

	public List<RoleDO> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleDO> roles) {
		this.roles = roles;
	}

}
