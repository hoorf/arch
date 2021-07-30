package org.github.hoorf.spring.bean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MyBeanRegistryProcessor implements BeanDefinitionRegistryPostProcessor {

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {

        RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(TestServiceImpl.class);
        registry.registerBeanDefinition("testService", rootBeanDefinition);
        log.info("执行了");
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

        //可以忽略,不带Bean注册功能的BeanFactory才会执行到,在BeanDefinitionRegistryPostProcessor定义中
    }
}
