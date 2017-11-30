package es.usc.citius.utils.generator.annotations;

import es.usc.citius.utils.generator.generators.StringGenerator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface RandomString {
    Class<?> generator() default StringGenerator.class;
    String [] from() default {};
    int count() default -1;
    int minCount() default 3;
    int maxCount() default 5;
    int length() default -1;
    int maxLength () default 5;
    int minLength () default 15;
}
