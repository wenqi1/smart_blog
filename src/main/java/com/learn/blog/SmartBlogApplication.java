package com.learn.blog;

import com.learn.blog.config.DynamicDataSourceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Import;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@Import({DynamicDataSourceConfig.class})
@MapperScan("com.learn.blog.dao")
public class SmartBlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(SmartBlogApplication.class, args);
    }

}
