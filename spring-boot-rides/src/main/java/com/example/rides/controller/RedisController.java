package com.example.rides.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/redis")
public class RedisController {

    @RequestMapping("")
    @ResponseBody
    public void redisTest(){

    }

}
