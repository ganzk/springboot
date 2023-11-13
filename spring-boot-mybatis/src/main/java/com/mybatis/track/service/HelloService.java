package com.mybatis.track.service;

import com.mybatis.track.domin.UserDo;

import java.util.List;

public interface HelloService {

    List<UserDo> seyHello();

    UserDo findOne(int id);

    int updateOne(UserDo userDo);

}
