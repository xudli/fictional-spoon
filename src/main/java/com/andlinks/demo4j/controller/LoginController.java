package com.andlinks.demo4j.controller;

import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by 王凯斌 on 2017/3/3.
 * 登录相关controller
 */
@Controller
public class LoginController implements ErrorController {


    private static final String PATH = "/error";

    @RequestMapping(value="/login")
    public ModelAndView login(HttpServletRequest request, Map<String, Object> map) throws Exception {


        String exception = (String) request.getAttribute("shiroLoginFailure");

        String msg = "";
        if (exception != null) {
            if (UnknownAccountException.class.getName().equals(exception)) {
                msg = "账号不存在!";
            } else if (IncorrectCredentialsException.class.getName().equals(exception)) {
                msg = "用户名或密码错误！";
            } else if (DisabledAccountException.class.getName().equals(exception)) {
                msg = "该用户已被删除";
            } else {
                msg = "未知错误";
            }
        }
        map.put("msg", msg);
        // 此方法不处理登录成功,由shiro进行处理.
        ModelAndView mv =new ModelAndView("/admin/loginPage",map);
        return mv;
    }

    @RequestMapping(value="/unAuthorized")
    public String unAuthorized(){
        return "/admin/unAuthorized";
    }

    @RequestMapping(value=PATH)
    public String error(){
        return "redirect:/index.html";
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }


}
