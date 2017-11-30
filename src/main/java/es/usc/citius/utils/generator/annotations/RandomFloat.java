package es.usc.citius.utils.generator.annotations;

import es.usc.citius.utils.generator.generators.FloatGenerator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface RandomFloat {
    Class<?> generator() default FloatGenerator.class;
    float [] from() default {};
    int count() default -1;
    int minCount() default 3;
    int maxCount() default 5;
    float min() default Float.MIN_VALUE;
    float max() default Float.MAX_VALUE;
}
