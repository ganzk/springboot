package com.mybatis.track.service.impl;

import com.mybatis.track.dao.HelloDao;
import com.mybatis.track.domin.UserDo;
import com.mybatis.track.service.HelloService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class HelloServiceImpl implements HelloService {

    @Resource
    HelloDao helloDao;

    @Override
    public List<UserDo> seyHello() {
        return helloDao.sayHello();
    }

    @Override
    public UserDo findOne(int id) {
        return helloDao.findOne(id);
    }

}
