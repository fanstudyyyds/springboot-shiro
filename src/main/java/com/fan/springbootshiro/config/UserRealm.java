package com.fan.springbootshiro.config;

import com.fan.springbootshiro.dao.UserDao;
import com.fan.springbootshiro.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

/*
 * @author  fan
 * @date  2021/9/11 14:31
 * @aa  玉树临风美少年,揽镜自顾夜不眠
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    UserDao userDao;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了授权>>>>>>>>>>>>>");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //info.addStringPermission("user:add");
        //拿到当前登入的对象
        Subject subject = SecurityUtils.getSubject();
        User principal = (User) subject.getPrincipal();
        info.addStringPermission(principal.getPerms());
        return info;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行了认证>>>>>>>>>>>>>");
        //用户名密码

        //  String name = "root";
        //   String passwoed = "root";


        UsernamePasswordToken userToken = (UsernamePasswordToken) authenticationToken;
        User user = userDao.queryByName(userToken.getUsername());

        if (user == null) {//没有这个人
            return null;
        }

        //密码认证shiro做
        //可以加密
        return new SimpleAuthenticationInfo(user, user.getPwd(), "");
    }
}
