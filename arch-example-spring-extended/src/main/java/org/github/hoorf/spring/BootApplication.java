package org.github.hoorf.spring;

import org.github.hoorf.spring.bean.TestServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class BootApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(BootApplication.class, args);
        TestServiceImpl bean = (TestServiceImpl)context.getBean("testService");
        bean.hello();

    }
}
