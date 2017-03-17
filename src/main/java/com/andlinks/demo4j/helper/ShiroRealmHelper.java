package com.andlinks.demo4j.helper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andlinks.demo4j.ShiroRealm;
import com.andlinks.demo4j.dao.PermissionMapper;
import com.andlinks.demo4j.dao.RoleMapper;
import com.andlinks.demo4j.dao.UserMapper;
import com.andlinks.demo4j.model.PermissionDO;
import com.andlinks.demo4j.model.RoleDO;
import com.andlinks.demo4j.model.UserDO;

@Service
public class ShiroRealmHelper {

	private static final Logger logger = LoggerFactory.getLogger(ShiroRealm.class);

	@Autowired
	private ShiroRealm shiroRealm;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private PermissionMapper permissionMapper;

	/**
	 * 根据useruuid删除授权缓存
	 * 
	 * @param userUuid
	 */
	public void clearCachedAuthorizationByUserUuid(String userUuid) {
		try {
			UserDO user = userMapper.getByUuid(userUuid);
			List<UserDO> userList = new ArrayList<UserDO>();
			userList.add(user);
			this.clearCachedAuthorizationInfo(userList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据roleuuid删除授权缓存
	 * 
	 * @param roleUuid
	 */
	public void clearCachedAuthorizationByRoleUuid(String roleUuid) {
		try {
			RoleDO role = roleMapper.getByUuid(roleUuid);
			this.clearCachedAuthorizationInfo(role.getUsers());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据permissionuuid删除授权缓存
	 * 
	 * @param permissionUuid
	 */
	public void clearCachedAuthorizationByPermissionUuid(String permissionUuid) {
		try {
			PermissionDO permission = permissionMapper.getByUuid(permissionUuid);
			if (permission != null && permission.getRoles() != null && permission.getRoles().size() > 0) {
				Set<UserDO> userSet = new HashSet<UserDO>();
				permission.getRoles().forEach(n -> {
					if (n != null) {
						RoleDO role = roleMapper.getByUuid(n.getUuid());
						userSet.addAll(role.getUsers());
					}
				});
				this.clearCachedAuthorizationInfo(userSet);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 更具uesrlist删除授权缓存
	 * 
	 * @param userList
	 */
	public void clearCachedAuthorizationInfo(Collection<UserDO> userList) {
		if (userList != null && userList.size() > 0) {
			logger.info("清除【授权】缓存之前");
			Cache<Object, AuthorizationInfo> c = shiroRealm.getAuthorizationCache();
			for (Object o : c.keys()) {
				logger.info(o + " , " + c.get(o));
			}
			userList.forEach(n -> {
				SimplePrincipalCollection spc = new SimplePrincipalCollection(n, shiroRealm.getName());
				shiroRealm.clearCachedAuthorizationInfo(spc);
			});
			logger.info("清除【授权】缓存之后");
			int cacheSize = c.keys().size();
			logger.info("【授权】缓存的大小:" + cacheSize);

			for (Object o : c.keys()) {
				logger.info(o + " , " + c.get(o));
			}
			if (cacheSize == 0) {
				logger.info("说明【授权】缓存被清空了。");
			}
		}
	}
}
