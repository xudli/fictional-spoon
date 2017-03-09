package com.andlinks.demo4j.controller;

import java.util.List;

import com.andlinks.demo4j.model.UserDO;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.andlinks.demo4j.model.RoleDO;
import com.andlinks.demo4j.service.RoleService;

/**
 * 角色相关 controller
 * 
 * @author lixudong
 *
 */
@RestController
@RequestMapping("/api")
public class RoleController {

	@Autowired
	private RoleService roleService;

	@RequiresPermissions("role:read")
	@RequestMapping(value = "/role/{uuid}", method = RequestMethod.GET)
	public RoleDO get(@PathVariable String uuid) {
		return roleService.getByUuid(uuid);
	}

	@RequiresPermissions("role:read")
	@RequestMapping(value = "/role", method = RequestMethod.GET)
	public List<RoleDO> list() {
		return roleService.list();
	}

	@RequestMapping(value = "/roleForUser", method = RequestMethod.GET)
	public List<RoleDO> listForUser() {
		return roleService.list();
	}

	@RequiresPermissions("role:write")
	@RequestMapping(value="/role",method = RequestMethod.POST)
	public String save(RoleDO role){

		return roleService.save(role);
	}

	@RequiresPermissions("role:write")
	@RequestMapping(value="/role/{uuid}",method = RequestMethod.PUT)
	public String update(@PathVariable String uuid,RoleDO role){

		role.setUuid(uuid);
		roleService.update(role);
		return "success";
	}

	@RequiresPermissions("role:write")
	@RequestMapping(value="/role/{uuid}",method = RequestMethod.DELETE)
	public String delete(@PathVariable String uuid){

		roleService.remove(uuid);
		return "success";
	}

	@RequiresPermissions("role:write")
	@RequestMapping(value="/role/{uuid}/permissions",method = RequestMethod.PUT)
	public String updatePermissions(@PathVariable String uuid,String[] permissionUuids){

		roleService.updatePermission(uuid,permissionUuids);
		return "success";
	}

	@ResponseStatus(value= HttpStatus.FORBIDDEN, reason="您没有此操作的权限")
	@ExceptionHandler(AuthorizationException.class)
	public String handleError() {

		return "您没有此操作的权限";
	}
}
