package com.andlinks.demo4j;

import com.andlinks.demo4j.model.PermissionDO;
import com.andlinks.demo4j.model.RoleDO;
import com.andlinks.demo4j.model.UserDO;
import com.andlinks.demo4j.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

/**
 * Created by 王凯斌 on 2017/3/3.
 * shiro的realm控制
 */
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Override
    protected  AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals){
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        UserDO userDO  = (UserDO)principals.getPrimaryPrincipal();
        for(RoleDO roleDO:userDO.getRoles()) {
            authorizationInfo.addRole(roleDO.getRoleName());
        }
        for(PermissionDO permissionDO:userDO.getPermissionDOs()){
            authorizationInfo.addStringPermission(permissionDO.getPermissionName());
        }
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        UsernamePasswordToken authenticationToken = (UsernamePasswordToken) token;
        String userName = (String)authenticationToken.getPrincipal();
        String password = String.valueOf(authenticationToken.getPassword());
        UserDO userDO = userService.getByUserName(userName);
        if(userDO==null){
            throw new UnknownAccountException();//没找到帐号
        }
        if (!DigestUtils.md5Hex(password).equals(userDO.getPassword())) {
            throw new IncorrectCredentialsException();
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                userDO, //用户名
                password, //密码
                getName()  //realm name
        );
        return authenticationInfo;
    }
}
