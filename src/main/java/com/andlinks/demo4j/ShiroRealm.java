package com.andlinks.demo4j;

import java.util.Collection;

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
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	private static final Logger logger = LoggerFactory.getLogger(ShiroRealm.class);

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
	
	 public void doClearCache(Collection<UserDO> userList) {
		 if(userList!=null&&userList.size()>0){
			 userList.forEach(n->{
				 SimplePrincipalCollection spc = new SimplePrincipalCollection(n, getName());
				 super.doClearCache(spc);
			 });
		 }
	 }
	

	public void clearCachedAuthorizationInfo(Collection<UserDO> userList) {
		 if(userList!=null&&userList.size()>0){
			 logger.info("清除【授权】缓存之前");
				Cache<Object, AuthorizationInfo> c = getAuthorizationCache();
				for (Object o : c.keys()) {
					logger.info(o + " , " + c.get(o));
				}
				userList.forEach(n->{
					SimplePrincipalCollection spc = new SimplePrincipalCollection(n, getName());
					super.clearCachedAuthorizationInfo(spc);
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
