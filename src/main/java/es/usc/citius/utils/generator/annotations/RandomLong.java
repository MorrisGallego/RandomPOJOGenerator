package es.usc.citius.utils.generator.annotations;

import es.usc.citius.utils.generator.generators.LongGenerator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface RandomLong {
    Class<?> generator() default LongGenerator.class;
    long [] from() default {};
    int count() default -1;
    int minCount() default 3;
    int maxCount() default 5;
    long min() default Long.MIN_VALUE;
    long max() default Long.MAX_VALUE;
}
