package com.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 映射配置文件
 * 将application1.properties映射到VegetableConfig
 *
 *
 * @Configuration
 * 声明是一个配置类，Spring的注解启动,不用放在启动类的@EnableConfigurationProperties({})注解里面
 * @@ConfigurationProperties(prefix = "vegetable")
 * 声明这是一个配置文件类，并且声明前缀
 * @PropertySource("classpath:application1.properties")
 * 申明配置文件的地址
 */
@Configuration
@ConfigurationProperties(prefix = "vegetable")
@PropertySource("classpath:application1.properties")
public class VegetableConfig {

    private String potato;

    private String eggplant;

    private String greenpeper;

    public String getPotato() {
        return potato;
    }

    public void setPotato(String potato) {
        this.potato = potato;
    }

    public String getEggplant() {
        return eggplant;
    }

    public void setEggplant(String eggplant) {
        this.eggplant = eggplant;
    }

    public String getGreenpeper() {
        return greenpeper;
    }

    public void setGreenpeper(String greenpeper) {
        this.greenpeper = greenpeper;
    }
}
