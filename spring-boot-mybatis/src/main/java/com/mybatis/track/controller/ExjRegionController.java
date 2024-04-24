package com.mybatis.track.controller;

import com.mybatis.track.domin.ExjRegionPo;
import com.mybatis.track.domin.UserDo;
import com.mybatis.track.service.ExjRegionService;
import com.mybatis.track.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@ResponseBody
@Controller
public class ExjRegionController {

    @Autowired
    ExjRegionService exjRegionService;

    @RequestMapping("/exj")
    public List<ExjRegionPo> hello(){
        List<ExjRegionPo> exjRegionPos = exjRegionService.selectAll();
        List<ExjRegionPo> exjRegionPos1 = exjRegionService.selectParent();

        List<ExjRegionPo> addressLoop = exjRegionService.findAddressLoop(exjRegionPos1, exjRegionPos);


        return addressLoop;
    }

}
