package com.mybatis.track.dao.impl;

import com.mybatis.track.dao.HelloDao;
import com.mybatis.track.domin.UserDo;
import com.mybatis.track.mapper.ExjRegionMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class HelloDaoImpl implements HelloDao {

    @Resource
    private ExjRegionMapper exjRegionMapper;

    @Override
    public List<UserDo> sayHello() {
        return null;
    }

    @Override
    public UserDo findOne(int id) {
        return null;
    }
}
