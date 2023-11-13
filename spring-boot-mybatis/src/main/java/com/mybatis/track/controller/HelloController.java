package com.mybatis.track.controller;

import com.mybatis.track.domin.UserDo;
import com.mybatis.track.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@ResponseBody
@Controller
public class HelloController {

    @Autowired
    HelloService helloService;

    @RequestMapping("/hello")
    public String hello(){
        List<UserDo> userDos = helloService.seyHello();
        return userDos.get(0).getName();
    }

    @RequestMapping("/fineOne/{id}")
    public String fineOne(@PathVariable(value = "id") int id){
        UserDo userDo = helloService.findOne(id);
        return userDo.getName();
    }

    @RequestMapping("/updateOne/{id}")
    public String updateOne(@PathVariable(value = "id") int id){
        UserDo userDo = helloService.findOne(id);
        return userDo.getName();
    }

}
