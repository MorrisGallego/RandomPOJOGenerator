package es.usc.citius.utils.generator.annotations;

import es.usc.citius.utils.generator.generators.DoubleGenerator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface RandomDouble {
    Class<?> generator() default DoubleGenerator.class;
    double [] from() default {};
    int count() default -1;
    int minCount() default 3;
    int maxCount() default 5;
    double min() default Double.MIN_VALUE;
    double max() default Double.MAX_VALUE;
}
