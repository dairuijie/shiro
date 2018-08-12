package com.dai.ruijie.core;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import com.dai.ruijie.pojo.Users;


public class ShiroDbReaml extends AuthorizingRealm{

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.err.println("授权");
        List<String> list = new ArrayList<String>(); 
        list.add("user:add");
        list.add("user:delete");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        for(String perm : list) {
            info.addStringPermission(perm);
        }
        info.addRole("admin");
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordCaptchaToken atoken = (UsernamePasswordCaptchaToken) token;
        String userName = (String) atoken.getPrincipal();
        System.out.println("userName=" + userName);
        String pwd = "658d128f20ee88e00e9607a475cdfaa5";
        String salt = "123";
        String captcha = atoken.getCaptcha();
        String exitCode = (String) SecurityUtils.getSubject().getSession()
                .getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
        if (null == captcha || !captcha.equalsIgnoreCase(exitCode)) {
            throw new CaptchaException("验证码错误");
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(new Users(userName,pwd), pwd,ByteSource.Util.bytes(salt), getName());
        return info;
    }
    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return "shiroDbRealm";
    }

}
