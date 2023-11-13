package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyErrorController {

    @RequestMapping("/error404")
    public String error(){
        return "thymeleaf/error404";
    }

    @RequestMapping("/testError")
    public String error1(){
        int i = 1/0;
        return "thymeleaf/error404";
    }

}
