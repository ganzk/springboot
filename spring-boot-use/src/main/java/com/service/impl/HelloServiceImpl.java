package com.service.impl;

import com.service.HelloService;
import com.dao.HelloMapper;
import com.dao.HelloRepository;
import com.domain.IPhone;
import com.domain.StudentDo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class HelloServiceImpl implements HelloService {

    @Autowired
    HelloMapper helloMapper;

    @Resource
    HelloRepository helloRepository;

    @Override
    public IPhone getIPhone() {
        IPhone iPhone = helloMapper.findIPhone();
        return iPhone;
    }

    /**
     *
     * @Cacheable value 缓存名称
     * @return
     */
    @Override
    @Cacheable("student")
    public StudentDo getStudent() {
        log.info("查询student");
        StudentDo student = helloRepository.findStudent();
        return student;
    }

}
