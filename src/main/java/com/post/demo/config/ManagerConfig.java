package com.post.demo.config;

import com.post.demo.manager.MessageManager;
import com.post.demo.manager.MessageManagerImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ManagerConfig {

    @Bean(name = "messagerManager")
    public MessageManager messageManager(){
        MessageManagerImpl messageManager = new MessageManagerImpl();
        return messageManager;
    }
}
