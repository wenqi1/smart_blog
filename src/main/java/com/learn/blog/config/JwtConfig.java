package com.learn.blog.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * jwt配置类
 */
@ConfigurationProperties("jwt.configuration")
@Component
@Data
public class JwtConfig {
    private String permission = "permission";
    private String salt;
    private int expiration;
    private String publicKey;
    private String privateKey;
}
