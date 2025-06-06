package com.iolab.musicstore.infrastructure.util;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

public class MessageUtil {

    private static MessageSource messageSource;

    private static MessageUtil instance;

    private MessageUtil(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public static MessageUtil getInstance(MessageSource messageSource) {
        if (instance == null) {
            synchronized (MessageUtil.class) {
                instance = new MessageUtil(messageSource);
            }
        }
        return instance;
    }

    public static String getMessage(String code) {
        return messageSource.getMessage(code, null, LocaleContextHolder.getLocale());
    }

    public static String getMessage(String code, Object... args) {
        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }
 }
