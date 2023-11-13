package com.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 自定义配置
 * 注解@ConfigurationProperties
 * 其中里面的prefix指向一级目录
 */
@ConfigurationProperties(prefix = "food")
@Data
public class FoodConfig {

    private String rice;
    private String meat;
    private String[] sauce;




}
