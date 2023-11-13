package com.controller;

import com.config.FoodConfig;
import com.config.VegetableConfig;
import com.domain.Food;
import com.domain.IPhone;
import com.domain.StudentDo;
import com.domain.VegetableDo;
//import com.track.domain.*;
import com.service.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/hello")
@Slf4j
public class HelloController {

    /**
     * @Value("${}") 用来获取配置文件里面的配置的常量，
     */
//    @Value("${food.rice}")
//    private String rice;
//    @Value("${food.meat}")
//    private String meat;

//     @Value("${info.username}")
//     private String username;
//
//     @Value("${info.password}")
//     private String password;

    // 需要引入配置文件类，才能去拿到配置的常量值
    @Autowired
    private FoodConfig foodConfig;

    @Autowired
    private VegetableConfig vegetableConfig;

    @Autowired
    private HelloService helloService;

    @RequestMapping("/test")
    @ResponseBody
    public String test(){
        System.out.println("test");
        return "hello world";
    }

    @RequestMapping("/json")
    @ResponseBody
    public Food testJson(){
        System.out.println("json");
        Food food = new Food();
        food.setMeet(foodConfig.getMeat());
        food.setRice(foodConfig.getRice());
        food.setSauce(foodConfig.getSauce());
        return food;
    }

    @RequestMapping("/vegetable")
    @ResponseBody
    public VegetableDo vegetable(){
        VegetableDo vegetableDo = new VegetableDo();
        vegetableDo.setPotato(vegetableConfig.getPotato());
        vegetableDo.setEggplant(vegetableConfig.getEggplant());
        vegetableDo.setGreenpeper(vegetableConfig.getGreenpeper());
        return vegetableDo;
    }

//    @RequestMapping("/jasypt")
//    @ResponseBody
//    public String jasypt(){
//        return "username:" + username + " " + "password:" + password ;
//    }

    @RequestMapping("/iphone")
    @ResponseBody
    public IPhone iphone(){
        System.out.println("iPhone");
        IPhone iPhone = helloService.getIPhone();
        return iPhone;
    }

    @RequestMapping("/student")
    @ResponseBody
    public StudentDo student(){
        System.out.println("student");
        return helloService.getStudent();
    }

    /**
     * thymeleaf
     * @return
     */
    @RequestMapping("/thymeleaf")
    public String hello(){
        log.info("我是日志内容");
        System.out.println("hello==thymeleaf");
        return "thymeleaf/hello";
    }


}
