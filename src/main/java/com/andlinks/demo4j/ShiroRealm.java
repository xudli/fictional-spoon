package com.andlinks.demo4j;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.andlinks.demo4j.model.PermissionDO;
import com.andlinks.demo4j.model.RoleDO;
import com.andlinks.demo4j.model.UserDO;
import com.andlinks.demo4j.service.UserService;

/**
 * Created by 王凯斌 on 2017/3/3. shiro的realm控制
 */
public class ShiroRealm extends AuthorizingRealm {
	@Autowired
	private UserService userService;

	/*
	 * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		UserDO userDo = (UserDO) principals.getPrimaryPrincipal();

		// 如果权限信息更改，需从数据库中提取新的权限配置
		UserDO userDONew = userService.getByUuid(userDo.getUuid());
		for (RoleDO roleDO : userDONew.getRoles()) {
			authorizationInfo.addRole(roleDO.getRoleName());
		}
		for (PermissionDO permissionDO : userDONew.getPermissionDOs()) {
			authorizationInfo.addStringPermission(permissionDO.getPermissionName());
		}
		return authorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

		UsernamePasswordToken authenticationToken = (UsernamePasswordToken) token;
		String userName = (String) authenticationToken.getPrincipal();
		String password = String.valueOf(authenticationToken.getPassword());
		UserDO userDO = userService.getByUserName(userName);
		if (userDO == null) {
			throw new UnknownAccountException();// 没找到帐号
		}
		if (userDO.getDeleted()) {
			throw new DisabledAccountException();
		}
		if (!DigestUtils.md5Hex(password).equals(userDO.getPassword())) {
			throw new IncorrectCredentialsException();
		}
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(userDO, // 用户名
				password, // 密码
				getName() // realm name
		);
		return authenticationInfo;
	}

	/*
	 * 清除授权缓存
	 */
	@Override
	public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
		super.clearCachedAuthorizationInfo(principals);
	}
}
