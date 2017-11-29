package es.usc.citius.utils.generator.annotations;

import es.usc.citius.utils.generator.GenerationStrategy;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface AllowedValues {
    GenerationStrategy value() default GenerationStrategy.PICK;
    String[] strings() default {};
    long[] longs() default {};
    int[] ints() default {};
    double[] doubles() default {};
    float[] floats() default {};
    double min() default Double.MIN_VALUE;
    double max() default Double.MAX_VALUE;
    int length() default -1;
    int maxLength () default Integer.MAX_VALUE;
    int minLength () default 0;
}
