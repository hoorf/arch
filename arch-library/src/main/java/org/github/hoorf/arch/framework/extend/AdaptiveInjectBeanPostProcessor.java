package org.github.hoorf.arch.framework.extend;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;

import static org.github.hoorf.arch.framework.extend.AdaptiveInject.*;


@Component
@Slf4j
public class AdaptiveInjectBeanPostProcessor implements ApplicationContextAware, BeanPostProcessor, Ordered {


    private ApplicationContext applicationContext;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        log.info(beanName);
        ReflectionUtils.doWithFields(bean.getClass(), fc -> {
            ReflectionUtils.makeAccessible(fc);
            AdaptiveInject annotation = fc.getAnnotation(AdaptiveInject.class);
            Class<?> injectType = fc.getType();
            Object targetObj = getTargetBean(injectType, annotation);
            ReflectionUtils.setField(fc, bean, targetObj);
        }, f -> f.isAnnotationPresent(AdaptiveInject.class));
        return bean;
    }

    private Object getTargetBean(Class<?> injectType, AdaptiveInject annotation) {
        String[] beanNames = applicationContext.getBeanNamesForType(injectType);
        for (String beanName : beanNames) {
            Object bean = applicationContext.getBean(beanName);

            boolean isController = AnnotationUtils.findAnnotation(bean.getClass(), Controller.class) != null;
            boolean isRpc = AnnotationUtils.findAnnotation(bean.getClass(), FeignClient.class) != null;

            switch (annotation.mode()) {
                case LOCAL_MODE: {
                    if (!isController && !isRpc) {
                        return bean;
                    }
                    break;
                }
                case RPC_MODE: {
                    if (isRpc) {
                        return bean;
                    }
                    break;
                }
                case AUTO_MODE: {
                    break;
                }
            }
        }

        Assert.isTrue(true, "do not find AdaptiveInject object ,please check config");

        return null;
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
