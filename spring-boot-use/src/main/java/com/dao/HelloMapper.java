package com.dao;

import com.domain.IPhone;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface HelloMapper {

    @Select("SELECT * FROM iphone WHERE id = 1")
    IPhone findIPhone();


}
