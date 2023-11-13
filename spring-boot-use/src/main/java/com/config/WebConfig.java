package com.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.web.context.WebServerApplicationContext;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

/**
 * 第一种  直接在配置文件里面进行配置
 * 第二种 修改web容器的参数（优先级高于配置文件）
 * 定制自己的web容器
 */
@Configuration
@Slf4j
public class WebConfig {

    @Bean
    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> customizer(){

        return new WebServerFactoryCustomizer<ConfigurableWebServerFactory>() {
            @Override
            public void customize(ConfigurableWebServerFactory factory) {
                factory.setPort(8089);
                // 配置错误文件
                ErrorPage errorPage = new ErrorPage(HttpStatus.NOT_FOUND,"/error404");
                factory.addErrorPages(errorPage);
            }
        };
    }

    /**
     * 获取容器类型
     * @param context
     * @return
     */
    @Bean
    public ApplicationRunner runner(WebServerApplicationContext context){
        return args -> {
            log.info("当前容器是：" + context.getWebServer().getClass().getName());
        };
    }


    /**
     * 会把容器内的bean注入进去，根据类型找到相应的bean，但是这个方法只会执行一次
     * @param component
     * @return
     */
//    @Bean
//    public ApplicationRunner runner(TestComponent component){
//        return args -> {
//            System.out.println("当前容器时" + component.getClass());
//        };
//    }

}
