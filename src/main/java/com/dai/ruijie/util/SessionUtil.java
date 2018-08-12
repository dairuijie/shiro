package com.dai.ruijie.util;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.dai.ruijie.pojo.Users;
/**
 * 
 * @ClassName:  SessionUtil   
 * @Description:TODO(获取用户信息)   
 * @author: drj 
 * @date:   2018年8月8日 下午11:31:40   
 *     
 * @Copyright: 2018 
 *
 */
public class SessionUtil {
    public static final Users getUser() {
        Subject subject = SecurityUtils.getSubject();
        if (null != subject) {
            return (Users) subject.getPrincipal();
        } else {
            return null;
        }
    }
}
