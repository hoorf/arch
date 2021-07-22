package org.github.hoorf.config;

import feign.Logger;
import lombok.extern.slf4j.Slf4j;
import org.github.hoorf.support.SpringContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class CommonAutoConfig implements CommandLineRunner {


    @Autowired
    private ApplicationContext applicationContext;


    @Override
    public void run(String... args) throws Exception {
        log.debug("init holder");
        SpringContextHolder.setApplicationContext(applicationContext);
    }

    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.BASIC;
    }


}
