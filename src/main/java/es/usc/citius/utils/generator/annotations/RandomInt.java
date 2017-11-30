package es.usc.citius.utils.generator.annotations;

import es.usc.citius.utils.generator.generators.IntegerGenerator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface RandomInt {
    Class<?> generator() default IntegerGenerator.class;
    int [] from() default {};
    int count() default -1;
    int minCount() default 3;
    int maxCount() default 5;
    int min() default Integer.MIN_VALUE;
    int max() default Integer.MAX_VALUE;
}
