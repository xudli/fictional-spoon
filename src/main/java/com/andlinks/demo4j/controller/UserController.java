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

import com.andlinks.demo4j.model.UserDO;
import com.andlinks.demo4j.service.UserService;

/**
 * Created by 王凯斌 on 2017/3/1. 用户相关controller
 */
@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserService userService;

	@RequiresPermissions("user:read")
	@RequestMapping(value = "/user/{uuid}", method = RequestMethod.GET)
	public UserDO get(@PathVariable String uuid) {

		return userService.getByUuid(uuid);
	}

	@RequiresPermissions("user:read")
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public List<UserDO> list() {

		return userService.list();
	}

	@RequiresPermissions("user:write")
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public String save(UserDO user) {

		return userService.save(user);
	}

	@RequiresPermissions("user:write")
	@RequestMapping(value = "/user/{uuid}", method = RequestMethod.PUT)
	public String update(@PathVariable String uuid, UserDO user) {

		user.setUuid(uuid);
		userService.update(user);
		return "success";
	}

	@RequiresPermissions("user:write")
	@RequestMapping(value = "/user/{uuid}/roles", method = RequestMethod.PUT)
	public String updateRoles(@PathVariable String uuid, String[] roleUuids) {

		userService.updateRoles(uuid, roleUuids);
		return "success";
	}

	@RequiresPermissions("user:write")
	@RequestMapping(value = "/user/{uuid}", method = RequestMethod.DELETE)
	public String delete(@PathVariable String uuid) {

		userService.remove(uuid);
		return "success";
	}

	@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "您没有此操作的权限")
	@ExceptionHandler(AuthorizationException.class)
	public String handleError() {

		return "您没有此操作的权限";
	}

	
}
