package com.andlinks.demo4j.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.andlinks.demo4j.ShiroRealm;
import com.andlinks.demo4j.dao.RoleMapper;
import com.andlinks.demo4j.model.RoleDO;
import com.andlinks.demo4j.service.RoleService;
import com.andlinks.demo4j.util.UuidUtils;

/**
 * Created by 王凯斌 on 2017/3/2. Role的serviceImlp
 */
@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleMapper roleMapper;

	@Autowired
	private ShiroRealm shiroRealm;

	@Override
	public RoleDO getById(Long id) {
		return roleMapper.getById(id);
	}

	@Override
	public RoleDO getByUuid(String uuid) {
		return roleMapper.getByUuid(uuid);
	}

	@Override
	public List<RoleDO> list() {
		return roleMapper.list();
	}

	@Override
	public String save(RoleDO roleDO) {

		String uuid = UuidUtils.getUUID(RoleDO.class);
		roleDO.setUuid(uuid);
		roleMapper.save(roleDO);
		return uuid;
	}

	@Override
	public void update(RoleDO roleDO) {
		roleMapper.update(roleDO);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updatePermission(String roleUuid, String[] permissionUuids) {

		this.clearAuthorizationCache(roleUuid);
		roleMapper.removePermissions(roleUuid);
		if (permissionUuids != null && permissionUuids.length != 0) {
			roleMapper.insertPermissions(roleUuid, permissionUuids);
		}
	}

	@Override
	public void remove(String uuid) {
		this.clearAuthorizationCache(uuid);
		roleMapper.remove(uuid);

	}

	/**
	 * 删除该角色关联的人员的授权缓存
	 * 
	 * @param roleUuid
	 */
	private void clearAuthorizationCache(String roleUuid) {
		try {
			RoleDO role = roleMapper.getByUuid(roleUuid);
			shiroRealm.clearCachedAuthorizationInfo(role.getUsers());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
