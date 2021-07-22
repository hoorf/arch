package org.github.hoorf.support;

import lombok.experimental.UtilityClass;
import org.springframework.context.ApplicationContext;

@UtilityClass
public class SpringContextHolder {
    static ApplicationContext applicationContext;

    static String[] basePackages;

    public String[] getBasePackages() {
        return basePackages;
    }

    public void setBasePackages(String[] packages) {
        basePackages = packages;
    }


    public void setApplicationContext(ApplicationContext context) {
        applicationContext = context;

    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 获取class对应的bean
     *
     * @param <T> 类型
     * @return bean
     */
    public <T> T getBean(Class<T> requiredType) {
        if (applicationContext == null) {
            return null;
        }
        return applicationContext.getBean(requiredType);
    }
}
