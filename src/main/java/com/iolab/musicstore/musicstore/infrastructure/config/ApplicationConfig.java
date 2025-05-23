package com.iolab.musicstore.musicstore.infrastructure.config;

import com.iolab.musicstore.musicstore.infrastructure.util.MessageUtil;
import org.apache.logging.log4j.message.Message;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
public class ApplicationConfig {

    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {
        var messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames("messages/message", "messages/error");
        messageSource.setDefaultEncoding("UTF-8");
        MessageUtil.getInstance(messageSource);
        return messageSource;
    }
}
