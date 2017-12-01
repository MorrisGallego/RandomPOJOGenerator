package es.usc.citius.utils.generator.annotations;

import es.usc.citius.utils.generator.generators.CharacterGenerator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface RandomCharacter {
    Class<?> generator() default CharacterGenerator.class;
    char [] from() default {};
    int count() default -1;
    int minCount() default 3;
    int maxCount() default 5;
}
