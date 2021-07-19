package com.group4.secbaselinebackend.utils.shiro;

import com.group4.secbaselinebackend.models.Admin;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * @author feng xl
 * @date 2021/7/17 0017 20:50
 */
public class MyShiroRealm extends AuthorizingRealm {

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        Object primaryPrincipal = principalCollection.getPrimaryPrincipal();
        if(primaryPrincipal == null) {
            throw new AuthenticationException("用户名为空, 登录失败!");
        }
        String name = primaryPrincipal.toString();
        Admin admin = new Admin();
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        Object principal = authenticationToken.getPrincipal();
        if(principal == null){
            throw new AuthenticationException("用户名为空, 登录失败!");
        }
        String name = principal.toString();
//        new SimpleAuthenticationInfo(name, "")
        return null;
    }
}
