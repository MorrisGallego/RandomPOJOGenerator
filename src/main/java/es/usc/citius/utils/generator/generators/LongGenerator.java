package es.usc.citius.utils.generator.generators;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface LongGenerator {
    long generate(Field field, long min, long max);
    default List<Long> generate(Field field, long min, long max, int count){
        return Stream.generate(() -> generate(field, min, max)).limit(count).collect(Collectors.toList());
    }
    default long[] generateArray(Field field, long min, long max, int count){
        long[] array = new long[count];
        for(int i=0; i<count; i++)
            array[i] =  generate(field, min, max);

        return array;
    }
}
