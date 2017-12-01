package es.usc.citius.utils.generator.annotations;

import es.usc.citius.utils.generator.generators.ShortGenerator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface RandomShort {
    Class<?> generator() default ShortGenerator.class;
    short [] from() default {};
    int count() default -1;
    int minCount() default 3;
    int maxCount() default 5;
    short min() default Short.MIN_VALUE;
    short max() default Short.MAX_VALUE;
}
