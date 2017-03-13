package com.andlinks.demo4j.model;

import java.util.List;
import java.util.Set;

/**
 * Created by 王凯斌 on 2017/3/1. 用户实体类
 */
public class UserDO extends BaseDO {

	private static final long serialVersionUID = 1953426709224582386L;

	private String userName;

	private String password;

	private List<RoleDO> roles;

	private Set<PermissionDO> permissionDOs;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<RoleDO> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleDO> roles) {
		this.roles = roles;
	}

	public Set<PermissionDO> getPermissionDOs() {
		return permissionDOs;
	}

	public void setPermissionDOs(Set<PermissionDO> permissionDOs) {
		this.permissionDOs = permissionDOs;
	}

}
