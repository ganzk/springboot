package com.example.dubbo.dubboserviceimpl;

import com.example.dubbo.server.IHelloService;
import com.track.service.HelloService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 供其他服务调用的，其实相当于之前的Controller
 */
@DubboService()
public class HelloDubboService implements HelloService {

    @Autowired
    IHelloService iHelloService;

    @Override
    public String sayHello(String s) {
        return iHelloService.sayHello(s);
    }
}
