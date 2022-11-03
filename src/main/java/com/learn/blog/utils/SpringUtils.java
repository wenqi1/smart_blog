package com.learn.blog.utils;

import org.springframework.aop.framework.AopContext;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

/**
 * Spring工具类,方便在非Spring管理环境中获取bean
 */
@Component
public class SpringUtils implements BeanFactoryPostProcessor, ApplicationContextAware {
    // Spring应用上下文环境
    private static ConfigurableListableBeanFactory beanFactory;

    private static ApplicationContext applicationContext;

    @Override
    public void postProcessBeanFactory(@NonNull ConfigurableListableBeanFactory beanFactory) throws BeansException {
        SpringUtils.beanFactory = beanFactory;
    }

    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) throws BeansException {
        SpringUtils.applicationContext = applicationContext;
    }

    /**
     * 根据名称获取对象
     *
     * @param name bean name
     * @return Object
     *
     */
    public static Object getBean(String name) {
        return beanFactory.getBean(name);
    }

    /**
     * 根据类型获取对象
     *
     * @param clazz bean class
     * @param <T> T bean class
     * @return T bean
     */
    public static <T> T getBean(Class<T> clazz) {
        return (T) beanFactory.getBean(clazz);
    }

    /**
     * 根据名称和类型获取对象
     *
     * @param name bean name
     * @param clazz bean class
     * @param <T> T bean class
     * @return T bean
     */
    public static <T> T getBean(String name, Class<T> clazz) {
        return beanFactory.getBean(name, clazz);
    }

    /**
     * beanFactory 是否包含指定名称的bean
     *
     * @param name bean name
     * @return 是否存在
     */
    public static boolean containsBean(String name) {
        return beanFactory.containsBean(name);
    }

    /**
     * 判断指定名称的bean是否为单例，如果指定名字的bean不存在，将会抛出NoSuchBeanDefinitionException
     *
     * @param name bean name
     * @return 是否为单例
     */
    public static boolean isSingleton(String name) {
        return beanFactory.isSingleton(name);
    }

    /**
     * 获取指定名称bean的类型
     *
     * @param name bean name
     * @return Class bean class
     */
    public static Class<?> getType(String name) {
        return beanFactory.getType(name);
    }

    /**
     * 获取aop代理对象
     *
     * @return Object
     */
    public static Object getAopProxy() {
        return AopContext.currentProxy();
    }

    /**
     * 获取当前的环境配置，无配置返回null
     *
     * @return 当前的环境配置
     */
    public static String[] getActiveProfiles() {
        return applicationContext.getEnvironment().getActiveProfiles();
    }
}
