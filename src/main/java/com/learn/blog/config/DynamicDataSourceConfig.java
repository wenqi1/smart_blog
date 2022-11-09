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
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

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
    public DataSource mysqlDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * 实例化数据源PostgreSQL
     *
     * @return DataSource
     */
    @Bean
    @ConfigurationProperties("spring.datasource.druid.postgresql")
    public DataSource postgresqlDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * 实例化DynamicDataSource
     *
     * @param mysqlDataSource mySQLDataSource
     * @param postgresqlDataSource postgresqlDataSource
     * @return DynamicDataSource
     */
    @Bean
    @Primary
    public DynamicDataSource dynamicDataSource(DataSource mysqlDataSource, DataSource postgresqlDataSource) {
        HashMap<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DataSourceNames.MYSQL, mysqlDataSource);
        targetDataSources.put(DataSourceNames.POSTGRESQL, postgresqlDataSource);

        return new DynamicDataSource(targetDataSources, mysqlDataSource);
    }

    /**
     * mysqlDataSource配置事务管理器
     *
     * @return PlatformTransactionManager
     */
    @Bean
    public PlatformTransactionManager mysqlTransactionManager(DataSource mysqlDataSource) {
        return new DataSourceTransactionManager(mysqlDataSource);
    }

    /**
     * postgresqlDataSource配置事务管理器
     *
     * @return PlatformTransactionManager
     */
    @Bean
    public PlatformTransactionManager postgresqlTransactionManager(DataSource postgresqlDataSource) {
        return new DataSourceTransactionManager(postgresqlDataSource);
    }

    @Bean
    @ConfigurationProperties(prefix = "mybatis.configuration")
    public org.apache.ibatis.session.Configuration configuration() {
        return new org.apache.ibatis.session.Configuration();
    }

    /**
     * 注入SqlSessionFactory
     *
     * @param dynamicDataSource dynamicDataSource
     * @return SqlSessionFactory
     * @throws Exception Exception
     */
    @Bean
    public SqlSessionFactory sqlSessionFactory(DynamicDataSource dynamicDataSource,
                                               org.apache.ibatis.session.Configuration configuration) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dynamicDataSource);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().
                getResources(MAPPER_LOCATION));
        sqlSessionFactoryBean.setTypeAliasesPackage("com.learn.blog.model");
        sqlSessionFactoryBean.setConfiguration(configuration);
        return sqlSessionFactoryBean.getObject();
    }

}
