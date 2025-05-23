package com.iolab.musicstore.musicstore.infrastructure.util;

import org.springframework.context.MessageSource;

import java.util.Locale;

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
        return messageSource.getMessage(code, null, Locale.ENGLISH);
    }
 }
