package com.learn.blog.annotation;

import com.learn.blog.enums.DataSourceNames;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 数据源开关，用来指定使用哪个数据源
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DataSourceSwitch {
    DataSourceNames name() default DataSourceNames.POSTGRESQL;
}
