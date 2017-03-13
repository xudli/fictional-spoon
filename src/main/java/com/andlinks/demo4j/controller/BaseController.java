package com.andlinks.demo4j.controller;

import org.apache.shiro.authz.AuthorizationException;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.apache.catalina.servlet4preview.http.MappingMatch.PATH;

/**
 * Created by wkb on 17-3-13.
 * controller的基类
 */
public class BaseController {


    @ResponseStatus(value= HttpStatus.FORBIDDEN, reason="您没有此操作的权限")
    @ExceptionHandler(AuthorizationException.class)
    public String handleError() {

        return "您没有此操作的权限";
    }
}
