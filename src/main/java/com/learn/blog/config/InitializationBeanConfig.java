package com.learn.blog.config;

import com.learn.blog.utils.SnowflakeIdUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 实例化Bean
 */
@Configuration
public class InitializationBeanConfig {
    @Value("${snowflake.workerId:1}")
    private long workerId;

    @Value("${snowflake.datacenterId:1}")
    private long datacenterId;

    @Bean
    public SnowflakeIdUtils snowflakeIdUtils() {
        return new SnowflakeIdUtils(workerId, datacenterId);
    }
}
