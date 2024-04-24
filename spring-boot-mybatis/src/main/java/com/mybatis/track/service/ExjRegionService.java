package com.mybatis.track.service;

import com.mybatis.track.domin.ExjRegionPo;
import com.mybatis.track.domin.UserDo;

import java.util.List;

public interface ExjRegionService {

    List<ExjRegionPo> selectAll();

    List<ExjRegionPo> selectParent();

    List<ExjRegionPo> findAddressLoop(List<ExjRegionPo> parentList,List<ExjRegionPo> allAddresses);


}
