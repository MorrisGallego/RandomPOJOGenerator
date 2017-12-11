package es.usc.citius.utils.generator.generators;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface CharacterGenerator {
    char generate(Field field);
    default List<Character> generate(Field field, int count){
        return Stream.generate(() -> this.generate(field)).limit(count).collect(Collectors.toList());
    }
    default char[] generateArray(Field field, int count){
        char[] array = new char[count];
        for(int i=0; i<count; i++)
            array[i] =  generate(field);

        return array;
    }
}
