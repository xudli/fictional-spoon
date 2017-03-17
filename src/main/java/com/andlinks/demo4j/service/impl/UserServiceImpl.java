package com.andlinks.demo4j.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.andlinks.demo4j.ShiroRealm;
import com.andlinks.demo4j.dao.UserMapper;
import com.andlinks.demo4j.model.UserDO;
import com.andlinks.demo4j.service.UserService;
import com.andlinks.demo4j.util.UuidUtils;

/**
 * Created by 王凯斌 on 2017/3/2. User的serviceImlp
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private ShiroRealm shiroRealm;

	@Override
	public UserDO getById(Long id) {
		return userMapper.getById(id);
	}

	@Override
	public UserDO getByUuid(String uuid) {
		return userMapper.getByUuid(uuid);
	}

	@Override
	public UserDO getByUserName(String userName) {
		return userMapper.getByUserName(userName);
	}

	@Override
	public List<UserDO> list() {
		return userMapper.list();
	}

	@Override
	public String save(UserDO user) {

		if (getByUserName(user.getUserName()) != null) {
			return null;
		}
		String uuid = UuidUtils.getUUID(UserDO.class);
		user.setUuid(uuid);
		user.setPassword(DigestUtils.md5Hex(user.getPassword()));
		userMapper.save(user);
		return uuid;
	}

	@Override
	public void update(UserDO user) {
		this.clearCache(user.getUuid());
		if (!StringUtils.isEmpty(user.getPassword())) {
			user.setPassword(DigestUtils.md5Hex(user.getPassword()));
			userMapper.update(user);
		} else {
			userMapper.updateWithoutPassword(user);
		}

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateRoles(String userUuid, String[] roleUuids) {
		this.clearAuthorizationCache(userUuid);
		userMapper.removeRoles(userUuid);
		if (roleUuids != null && roleUuids.length != 0) {
			userMapper.insertRoles(userUuid, roleUuids);
		}

	}

	@Override
	public void remove(String uuid) {
		this.clearCache(uuid);
		userMapper.remove(uuid);
	}

	/**
	 * 删除该人员的授权缓存
	 * 
	 * @param userUuid
	 */
	private void clearAuthorizationCache(String userUuid) {
		try {
			UserDO user = userMapper.getByUuid(userUuid);
			List<UserDO> userList = new ArrayList<UserDO>();
			userList.add(user);
			shiroRealm.clearCachedAuthorizationInfo(userList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 删除认证缓存和授权缓存 删除用户后;用户修改密码; 
	 * TODO:目前无法强制该用户logout
	 */
	private void clearCache(String userUuid) {
	}

}
