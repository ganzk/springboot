package com.example.rides;

import com.example.rides.config.RedisConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({RedisConfig.class})
public class RidesApplication {

    public static void main(String[] args) {
        RedisProperties.Jedis jedis = new RedisProperties.Jedis();
        SpringApplication.run(RidesApplication.class, args);
    }

}
