package com.andlinks.demo4j.controller;

import java.util.List;

import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.andlinks.demo4j.model.PermissionDO;
import com.andlinks.demo4j.service.PermissionService;

/**
 * 权限相关 controller
 * 
 * @author lixudong
 *
 */
@RestController
@RequestMapping("/api")
public class PermissionController extends BaseController {

	@Autowired
	private PermissionService permissionService;

	@RequiresPermissions("permission:read")
	@RequestMapping(value = "/permission/{uuid}", method = RequestMethod.GET)
	public PermissionDO get(@PathVariable String uuid) {
		return permissionService.getByUuid(uuid);
	}

	@RequiresPermissions("permission:read")
	@RequestMapping(value = "/permission", method = RequestMethod.GET)
	public List<PermissionDO> list() {
		return permissionService.list();
	}

	@RequestMapping(value = "/permissionForRole", method = RequestMethod.GET)
	public List<PermissionDO> listForRole() {
		return permissionService.list();
	}

	@RequiresPermissions("permission:write")
	@RequestMapping(value = "/permission", method = RequestMethod.POST)
	public String save(PermissionDO permission) {

		return permissionService.save(permission);
	}

	@RequiresPermissions("permission:write")
	@RequestMapping(value = "/permission/{uuid}", method = RequestMethod.PUT)
	public String update(@PathVariable String uuid, PermissionDO permission) {

		permission.setUuid(uuid);
		permissionService.update(permission);
		return "success";
	}

	@RequiresPermissions("permission:write")
	@RequestMapping(value = "/permission/{uuid}", method = RequestMethod.DELETE)
	public String delete(@PathVariable String uuid) {

		permissionService.remove(uuid);
		return "success";
	}

}
