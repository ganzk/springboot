package com.mybatis.track.dao.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.mybatis.track.dao.ExjRegionDao;
import com.mybatis.track.domin.ExjRegionPo;
import com.mybatis.track.mapper.ExjRegionMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class ExjRegionDaoImpl implements ExjRegionDao {

    @Resource
    private ExjRegionMapper exjRegionMapper;


    @Override
    public List<ExjRegionPo> selectAll() {
        return exjRegionMapper.selectList(
                Wrappers.<ExjRegionPo>lambdaQuery()
        );
    }

    @Override
    public List<ExjRegionPo> selectParent() {
        return exjRegionMapper.selectList(
                Wrappers.<ExjRegionPo>lambdaQuery()
                        .eq(ExjRegionPo::getParentId, "0")
        );
    }
}
