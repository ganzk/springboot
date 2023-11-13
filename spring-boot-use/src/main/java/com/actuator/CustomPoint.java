package com.actuator;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 自定义端点
 * @Endpoint 声明是一个端点
 * @ReadOperation
 *
 */
@Component
@Endpoint(id = "custom")
public class CustomPoint {

    /**
     * 需要返回json数据
     * @return
     */
    @ReadOperation
    @ResponseBody
    public Map<String,String> custom(){
        Map<String,String> map = new HashMap();
        map.put("name","hello custom endpoint");
        return map;
    }

}
