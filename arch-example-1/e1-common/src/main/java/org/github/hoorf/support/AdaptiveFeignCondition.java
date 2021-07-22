package org.github.hoorf.support;

import lombok.extern.slf4j.Slf4j;
import org.github.hoorf.util.ReflectionUtils;
import org.springframework.boot.autoconfigure.condition.ConditionOutcome;
import org.springframework.boot.autoconfigure.condition.SpringBootCondition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.stereotype.Controller;

import java.util.Set;

@Slf4j
public class AdaptiveFeignCondition extends SpringBootCondition {
    @Override
    public ConditionOutcome getMatchOutcome(ConditionContext context, AnnotatedTypeMetadata metadata) {
        ClassMetadata classMetadata = (ClassMetadata) metadata;

        String className = classMetadata.getClassName();
        Class interfaceClass = null;
        try {
            interfaceClass = Class.forName(className);
        } catch (ClassNotFoundException e) {
        }

        Set<Class<?>> classSet = ReflectionUtils.getSubTypesOf(interfaceClass);

        for (Class<?> implClass : classSet) {
            Controller annotation = AnnotationUtils.findAnnotation(implClass, Controller.class);
            if (null != annotation) {
               // log.debug("{} exits Controller.class ,no init feign {}", implClass.getName(), interfaceClass.getName());
                return ConditionOutcome.noMatch(new StringBuffer(implClass.getName()).append(" exits implement").toString());
            }
        }


        return ConditionOutcome.match();
    }
}
