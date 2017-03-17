package com.andlinks.demo4j.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.andlinks.demo4j.dao.RoleMapper;
import com.andlinks.demo4j.helper.ShiroRealmHelper;
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
	private ShiroRealmHelper shiroRealmHelper;

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

		shiroRealmHelper.clearCachedAuthorizationByRoleUuid(roleUuid);
		roleMapper.removePermissions(roleUuid);
		if (permissionUuids != null && permissionUuids.length != 0) {
			roleMapper.insertPermissions(roleUuid, permissionUuids);
		}
	}

	@Override
	public void remove(String uuid) {
		shiroRealmHelper.clearCachedAuthorizationByRoleUuid(uuid);
		roleMapper.remove(uuid);

	}

}
