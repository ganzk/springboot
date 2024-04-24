package com.mybatis.track.dao;

import com.mybatis.track.domin.UserDo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface HelloDao {

    List<UserDo> sayHello();

    UserDo findOne(int id);

}
