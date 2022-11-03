package com.learn.blog.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.learn.blog.enums.DataSourceNames;
import com.learn.blog.multisource.DynamicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.util.HashMap;

/**
 * 多数据源配置类
 */
@Configuration
public class DynamicDataSourceConfig {
    @Value("${mybatis.mapperLocations:classpath:mapper/mysql/*.xml}")
    private String MAPPER_LOCATION;

    /**
     * 实例化数据源MySQL
     *
     * @return DataSource
     */
    @Bean
    @ConfigurationProperties("spring.datasource.druid.mysql")
    public DataSource firstDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * 实例化数据源PostgreSQL
     *
     * @return DataSource
     */
    @Bean
    @ConfigurationProperties("spring.datasource.druid.postgresql")
    public DataSource secondDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * 实例化DynamicDataSource
     *
     * @param firstDataSource firstDataSource
     * @param secondDataSource secondDataSource
     * @return DynamicDataSource
     */
    @Bean
    @Primary
    public DynamicDataSource dynamicDataSource(DataSource firstDataSource, DataSource secondDataSource) {
        HashMap<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DataSourceNames.POSTGRESQL, firstDataSource);
        targetDataSources.put(DataSourceNames.MYSQL, secondDataSource);

        return new DynamicDataSource(targetDataSources, firstDataSource);
    }

    /**
     * 注入SqlSessionFactory
     *
     * @param dynamicDataSource dynamicDataSource
     * @return SqlSessionFactory
     * @throws Exception Exception
     */
    @Bean
    public SqlSessionFactory sqlSessionFactory(DynamicDataSource dynamicDataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dynamicDataSource);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().
                getResources(MAPPER_LOCATION));
        return sqlSessionFactoryBean.getObject();
    }

}
