package com.dai.ruijie.controller;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dai.ruijie.pojo.Users;
import com.dai.ruijie.util.SessionUtil;
import com.google.code.kaptcha.Producer;

@Controller
@RequestMapping("/")
public class IndexController {
    private final static Logger log = Logger.getLogger(IndexController.class);
    @Autowired
    private Producer captchaProducer;
    @RequestMapping("/toLogin")
    public ModelAndView toLogin(ModelAndView mv) {
        mv.setViewName("login");
        return mv;
    }

    @RequestMapping("/unauthorized")
    public ModelAndView unauthorized(ModelAndView mv) {
        mv.setViewName("unauthorized");
        return mv;
    }

    @RequestMapping("/login")
    public ModelAndView login(HttpServletRequest request, ModelAndView mv) {
        String className = (String) request.getAttribute("shiroLoginFailure");
        if (UnknownAccountException.class.getName().equals(className)) {
            mv.addObject("mag", "user login fail");
        } else if (IncorrectCredentialsException.class.getName().equals(className)) {
            mv.addObject("msg", "user login fail");
        } else {
            mv.addObject("msg", "系统异常");
        }
        return mv;
    }

    @RequestMapping("/index")
    public ModelAndView index(ModelAndView mv) {
        Users user = SessionUtil.getUser();
        mv.addObject("user", user.getUserName());
        mv.setViewName("index");
        return mv;
    }

    @RequestMapping("/list")
    @RequiresPermissions("user:list")
    public ModelAndView getList(ModelAndView mv) {
        mv.addObject("data", "ok");
        mv.setViewName("index");
        return mv;
    }

    @RequestMapping("/error")
    public String getError() {
        return "common/500";
    }

    /**
     * 生成验证码
     * 
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "captchaImage")
    public ModelAndView getKaptchaImage(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");
        String capText = captchaProducer.createText();
        session.setAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY, capText);
        BufferedImage bi = captchaProducer.createImage(capText);
        try {
            ServletOutputStream out = response.getOutputStream();
            ImageIO.write(bi, "jpg", out);
            log.debug("*****验证码: " + capText + "*****");
            try {
                out.flush();
            } finally {
                out.close();
            }
        } catch (Exception e) {
            log.error("getKaptchaImage fail !", e);
        }
        return null;
    }

}
