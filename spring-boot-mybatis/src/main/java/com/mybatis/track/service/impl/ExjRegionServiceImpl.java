package com.mybatis.track.service.impl;

import com.mybatis.track.dao.ExjRegionDao;
import com.mybatis.track.dao.HelloDao;
import com.mybatis.track.domin.ExjRegionPo;
import com.mybatis.track.domin.UserDo;
import com.mybatis.track.service.ExjRegionService;
import com.mybatis.track.service.HelloService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class ExjRegionServiceImpl implements ExjRegionService {

    @Resource
    ExjRegionDao exjRegionDao;

    @Override
    public List<ExjRegionPo> selectAll() {
        return exjRegionDao.selectAll();
    }

    @Override
    public List<ExjRegionPo> selectParent() {
        return exjRegionDao.selectParent();
    }

    @Override
    public List<ExjRegionPo> findAddressLoop(List<ExjRegionPo> parentList,List<ExjRegionPo> allAddresses){

        parentList.forEach(area->{

            List<ExjRegionPo> childes = allAddresses.stream().filter(add -> add.getParentId().equals(area.getId())).collect(Collectors.toList());

//            List<AddressCode> childes = this.findAddressCodeByParentId(area.getId());

            if(childes.isEmpty()) {
                return;
            }
            area.setChildAddressCodes(childes); // 这里不直接set 用list装填
            // ppt对象list装填
            findAddressLoop(childes,allAddresses);

        });


        return parentList;
    }


}
