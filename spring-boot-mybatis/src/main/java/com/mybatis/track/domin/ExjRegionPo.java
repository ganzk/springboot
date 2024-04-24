package com.mybatis.track.domin;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.List;

/**
 * ods员工表
 * @TableName ods_emp
 */
@TableName(value ="exj_region")
@Data
public class ExjRegionPo {

    @TableId
    private String id;


    private String regionName;


    private String parentId;


    @TableField(exist = false)
    private String parentName;


    private String py;


    private String pinYin;


    private String ownerId;


    private Integer fBizState;

    /**
     *子地区列表
     */
    @TableField(exist = false)
    private List<ExjRegionPo> childAddressCodes;

}