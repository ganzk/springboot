package com.mybatis.track.dao;

import com.mybatis.track.domin.ExjRegionPo;

import java.util.List;

public interface ExjRegionDao {

    List<ExjRegionPo> selectAll();

    List<ExjRegionPo> selectParent();

}
