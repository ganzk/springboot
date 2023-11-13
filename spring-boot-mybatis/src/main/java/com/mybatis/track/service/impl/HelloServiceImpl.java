package com.mybatis.track.service.impl;

import com.mybatis.track.dao.HelloMapper;
import com.mybatis.track.domin.UserDo;
import com.mybatis.track.service.HelloService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class HelloServiceImpl implements HelloService {

    @Resource
    HelloMapper helloMapper;

    @Override
    public List<UserDo> seyHello() {
        return helloMapper.sayHello();
    }

    @Override
    public UserDo findOne(int id) {
        return helloMapper.findOne(id);
    }

    @Override
    public UserDo updateOne(UserDo userDo) {
        return helloMapper.(id);
    }
}
