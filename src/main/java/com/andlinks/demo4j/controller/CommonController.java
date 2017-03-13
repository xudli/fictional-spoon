package com.andlinks.demo4j.controller;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by wkb on 17-3-13.
 */
@Controller
public class CommonController extends BaseController implements ErrorController {

    private static final String PATH = "/error";

    @RequestMapping(value=PATH)
    public String error(){
        return "redirect:/index.html";
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }
}
