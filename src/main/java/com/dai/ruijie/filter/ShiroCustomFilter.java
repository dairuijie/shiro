package com.dai.ruijie.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.log4j.Logger;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import com.dai.ruijie.core.UsernamePasswordCaptchaToken;

/**
 * 
 * @ClassName: ShiroCustomFilter
 * @Description:TODO(自定义shiro 过滤器 添加验证码)
 * @author: drj
 * @date: 2018年8月12日 下午10:44:35
 * 
 * @Copyright: 2018
 *
 */
public class ShiroCustomFilter extends FormAuthenticationFilter {
    public static final String DEFAULT_CAPTCHA_PARAM = "captcha";
    private static final Logger log = Logger.getLogger(ShiroCustomFilter.class);

    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
        String loginname = getUsername(request);
        String password = getPassword(request);
        String captcha = getCaptcha(request);
        boolean rememberMe = isRememberMe(request);
        String host = getHost(request);
        System.err.println("ShiroCustomFilter------------" + loginname + "------" + password);
        return new UsernamePasswordCaptchaToken(loginname, password.toCharArray(), rememberMe, host, captcha);
    }

    protected String getCaptcha(ServletRequest request) {
        return WebUtils.getCleanParam(request, DEFAULT_CAPTCHA_PARAM);
    }
}
