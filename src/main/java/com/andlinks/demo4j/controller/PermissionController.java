package com.andlinks.demo4j.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
public class PermissionController {

	@Autowired
	private PermissionService permissionService;

	@RequestMapping(value = "/permisson/{uuid}", method = RequestMethod.GET)
	public PermissionDO doGet(@PathVariable String uuid) {
		return permissionService.getByUuid(uuid);
	}

	@RequestMapping(value = "/permission", method = RequestMethod.GET)
	public List<PermissionDO> list() {
		return permissionService.list();
	}
	
	
}
