package com.andlinks.demo4j.controller;

import java.util.List;

import com.andlinks.demo4j.model.UserDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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

	@RequestMapping(value = "/permission/{uuid}", method = RequestMethod.GET)
	public PermissionDO get(@PathVariable String uuid) {
		return permissionService.getByUuid(uuid);
	}

	@RequestMapping(value = "/permission", method = RequestMethod.GET)
	public List<PermissionDO> list() {
		return permissionService.list();
	}

	@RequestMapping(value="/permission",method = RequestMethod.POST)
	public String save(PermissionDO	permission){

		return permissionService.save(permission);
	}

	@RequestMapping(value="/permission/{uuid}",method = RequestMethod.PUT)
	public String update(@PathVariable String uuid,PermissionDO	permission){

		permission.setUuid(uuid);
		permissionService.update(permission);
		return "success";
	}

	@RequestMapping(value="/permission/{uuid}",method = RequestMethod.DELETE)
	public String delete(@PathVariable String uuid){

		permissionService.remove(uuid);
		return "success";
	}


}
