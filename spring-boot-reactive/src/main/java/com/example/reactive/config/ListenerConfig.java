package com.example.reactive.config;

import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ListenerConfig {

    @EventListener(WebServerInitializedEvent.class)
    public void onWebServerReady(WebServerInitializedEvent event){
        System.out.println("当前容器是：" +
                event.getWebServer().getClass().getName());
    }

}
