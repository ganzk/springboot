package com.mybatis.track.dao;

import com.mybatis.track.domin.UserDo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
@Mapper
public interface HelloMapper {

    List<UserDo> sayHello();

    @Select("select name, age from test_student where id = #{id}")
    UserDo findOne(int id);

}
