package com.andlinks.demo4j.controller;

import java.util.List;

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
	public RoleDO doGet(@PathVariable String uuid) {
		return roleService.getByUuid(uuid);
	}

	@RequestMapping(value = "/role", method = RequestMethod.GET)
	public List<RoleDO> list() {
		return roleService.list();
	}

	@RequestMapping(value = "/role", method = RequestMethod.PUT)
	public String doSave(@RequestBody RoleDO role) {
		return roleService.save(role);
	}

	@RequestMapping(value = "/role", method = RequestMethod.POST)
	public void doUpdate(@RequestBody RoleDO role) {
		roleService.update(role);
	}

	@RequestMapping(value = "/role/{uuid}", method = RequestMethod.DELETE)
	public void doRemove(@PathVariable String uuid) {
		roleService.remove(uuid);
	}
}
