package es.usc.citius.utils.generator.generators;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface IntegerGenerator {
    int generate(Field field, int min, int max);
    default List<Integer> generate(Field field, int min, int max, int count){
        return Stream.generate(() -> generate(field, min, max)).limit(count).collect(Collectors.toList());
    }
    default int[] generateArray(Field field, int min, int max, int count){
        int[] array = new int[count];
        for(int i=0; i<count; i++)
            array[i] =  generate(field, min, max);

        return array;
    }
}
