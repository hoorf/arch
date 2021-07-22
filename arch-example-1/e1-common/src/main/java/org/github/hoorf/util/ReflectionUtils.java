package org.github.hoorf.util;


import lombok.extern.slf4j.Slf4j;
import org.github.hoorf.support.SpringContextHolder;
import org.reflections.Reflections;

import java.util.Set;

@Slf4j
public class ReflectionUtils extends org.reflections.ReflectionUtils {


    static Reflections reflections = null;

    static {
        String[] basePackages = SpringContextHolder.getBasePackages();
        log.debug("Scan base package {}",basePackages);
        reflections = new Reflections(basePackages);
    }


    public static <T> Set<Class<? extends T>> getSubTypesOf(Class<T> type) {
        return reflections.getSubTypesOf(type);
    }

}
