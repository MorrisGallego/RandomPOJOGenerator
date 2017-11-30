package es.usc.citius.utils.generator.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface RandomFloat {
    float [] from() default {};
    int count() default -1;
    int minCount() default 3;
    int maxCount() default 5;
    float min() default Float.MIN_VALUE;
    float max() default Float.MAX_VALUE;
}
