package es.usc.citius.utils.generator.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface RandomInt {
    int [] from() default {};
    int count() default -1;
    int minCount() default 3;
    int maxCount() default 5;
    int min() default Integer.MIN_VALUE;
    int max() default Integer.MAX_VALUE;
}
