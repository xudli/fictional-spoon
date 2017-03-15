package com.andlinks.demo4j.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andlinks.demo4j.ShiroRealm;
import com.andlinks.demo4j.dao.PermissionMapper;
import com.andlinks.demo4j.dao.RoleMapper;
import com.andlinks.demo4j.model.PermissionDO;
import com.andlinks.demo4j.model.RoleDO;
import com.andlinks.demo4j.model.UserDO;
import com.andlinks.demo4j.service.PermissionService;
import com.andlinks.demo4j.util.UuidUtils;

/**
 * Created by 王凯斌 on 2017/3/2. Permission的serviceImlp
 */
@Service
public class PermissionServiceImpl implements PermissionService {

	@Autowired
	private PermissionMapper permissionMapper;

	@Autowired
	private RoleMapper roleMapper;

	@Autowired
	private ShiroRealm shiroRealm;

	@Override
	public PermissionDO getById(Long id) {
		return permissionMapper.getById(id);
	}

	@Override
	public PermissionDO getByUuid(String uuid) {
		return permissionMapper.getByUuid(uuid);
	}

	@Override
	public List<PermissionDO> list() {
		return permissionMapper.list();
	}

	@Override
	public String save(PermissionDO permissionDO) {
		String uuid = UuidUtils.getUUID(PermissionDO.class);
		permissionDO.setUuid(uuid);
		permissionMapper.save(permissionDO);
		return uuid;
	}

	@Override
	public void update(PermissionDO permissionDO) {
		this.clearAuthorizationCache(permissionDO.getUuid());
		permissionMapper.update(permissionDO);
	}

	@Override
	public void remove(String uuid) {
		this.clearAuthorizationCache(uuid);
		permissionMapper.remove(uuid);
	}

	/**
	 * 删除该权限关联人员的授权缓存
	 * 
	 * @param permissionUuid
	 *            权限uuid
	 */
	private void clearAuthorizationCache(String permissionUuid) {
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
				shiroRealm.clearCachedAuthorizationInfo(userSet);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
