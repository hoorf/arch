package org.github.hoorf.core;

import java.lang.annotation.*;

@Inherited
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.CLASS)
public @interface DomainEntity {
}
