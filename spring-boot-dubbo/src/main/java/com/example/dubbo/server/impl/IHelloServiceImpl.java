package com.example.dubbo.server.impl;

import com.example.dubbo.server.IHelloService;
import org.springframework.stereotype.Service;

@Service
public class IHelloServiceImpl implements IHelloService {

    @Override
    public String sayHello(String msg) {
        System.out.println("终于执行了");
        return "hello," + msg;
    }

}
