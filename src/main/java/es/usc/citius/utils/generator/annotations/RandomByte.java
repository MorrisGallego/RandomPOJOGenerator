package es.usc.citius.utils.generator.annotations;

import es.usc.citius.utils.generator.generators.ByteGenerator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface RandomByte {
    Class<?> generator() default ByteGenerator.class;
    byte [] from() default {};
    int count() default -1;
    int minCount() default 3;
    int maxCount() default 5;
    byte min() default Byte.MIN_VALUE;
    byte max() default Byte.MAX_VALUE;
}
