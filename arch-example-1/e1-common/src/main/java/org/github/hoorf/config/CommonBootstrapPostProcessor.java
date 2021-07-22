package org.github.hoorf.config;

import lombok.extern.slf4j.Slf4j;
import org.github.hoorf.support.SpringContextHolder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.env.ConfigurableEnvironment;

@Slf4j
public class CommonBootstrapPostProcessor implements EnvironmentPostProcessor {
    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        Class<?> mainApplicationClass = application.getMainApplicationClass();
        SpringBootApplication annotation = AnnotationUtils.findAnnotation(mainApplicationClass, SpringBootApplication.class);
        String[] packages = annotation.scanBasePackages();
        if (null == packages || packages.length == 0) {
            String basePackage = mainApplicationClass.getPackage().getName();
            packages = new String[]{basePackage};
        }

        if (null == SpringContextHolder.getBasePackages() || SpringContextHolder.getBasePackages().length == 0) {
            SpringContextHolder.setBasePackages(packages);
        }
    }
}
