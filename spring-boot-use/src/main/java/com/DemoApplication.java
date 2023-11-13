package com;

import com.config.FoodConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;

// 声明入口类，并且表明Spring Boot项目
@SpringBootApplication
// 自动引入自定义的配置文件
@EnableConfigurationProperties({FoodConfig.class})
// 扫描servlet的注解，就会扫描到WebFilter的注解
@ServletComponentScan
// 打开缓存
@EnableCaching
public class DemoApplication {

    public static void main(String[] args) {
        // 使用SpringApplication.run方法来启动springboot
        // 参数当前类，main函数的参数
        ConfigurableApplicationContext run = SpringApplication.run(DemoApplication.class, args);
    }

}
