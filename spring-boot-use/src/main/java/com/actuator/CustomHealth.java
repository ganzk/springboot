package com.actuator;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

/**
 * 自定义health
 */
@Component
public class CustomHealth implements HealthIndicator {

    @Override
    public Health health() {
        int errorCode = 1;
        if(errorCode != 0){
            // 返回不健康的指标 并且返回code值
            return Health.down().withDetail("ErrorCode",errorCode).build();
        }
        return Health.up().build();
    }
}
