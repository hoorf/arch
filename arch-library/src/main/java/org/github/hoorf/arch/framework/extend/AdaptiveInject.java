package org.github.hoorf.arch.framework.extend;

import java.lang.annotation.*;

/**
 * 适配注入
 */
@Target(ElementType.FIELD)
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface AdaptiveInject {
    String LOCAL_MODE = "0";
    String RPC_MODE = "1";
    String AUTO_MODE = "2";

    String mode() default LOCAL_MODE;

}
