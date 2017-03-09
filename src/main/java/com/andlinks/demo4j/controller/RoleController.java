package com.andlinks.demo4j.controller;

import java.util.List;

import com.andlinks.demo4j.model.UserDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

	@RequestMapping(value = "/role/{uuid}", method = RequestMethod.GET)
	public RoleDO get(@PathVariable String uuid) {
		return roleService.getByUuid(uuid);
	}

	@RequestMapping(value = "/role", method = RequestMethod.GET)
	public List<RoleDO> list() {
		return roleService.list();
	}

	@RequestMapping(value="/role",method = RequestMethod.POST)
	public String save(RoleDO role){

		return roleService.save(role);
	}

	@RequestMapping(value="/role/{uuid}",method = RequestMethod.PUT)
	public String update(@PathVariable String uuid,RoleDO role){

		role.setUuid(uuid);
		roleService.update(role);
		return "success";
	}

	@RequestMapping(value="/role/{uuid}",method = RequestMethod.DELETE)
	public String delete(@PathVariable String uuid){

		roleService.remove(uuid);
		return "success";
	}

	@RequestMapping(value="/role/{uuid}/permissions",method = RequestMethod.PUT)
	public String updatePermissions(@PathVariable String uuid,String[] permissionUuids){

		roleService.updatePermission(uuid,permissionUuids);
		return "success";
	}
}
