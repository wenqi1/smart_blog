package com.learn.blog.utils;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

/**
 * 国际化message工具
 */
public class MessageUtils {
    private static final MessageSource messageSource = SpringUtils.getBean(MessageSource.class);

    /**
     * 根据中英文环境，获取到对于的中英文的message
     *
     * @return message
     */
    public static String getMessage(String code) {
        return messageSource.getMessage(code, null, LocaleContextHolder.getLocale());
    }
}
