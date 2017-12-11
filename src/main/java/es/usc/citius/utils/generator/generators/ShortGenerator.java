package es.usc.citius.utils.generator.generators;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface ShortGenerator {
    short generate(Field field, short min, short max);
    default List<Short> generate(Field field, short min, short max, int count){
        return Stream.generate(() -> generate(field, min, max)).limit(count).collect(Collectors.toList());
    }
    default short[] generateArray(Field field, short min, short max, int count){
        short[] array = new short[count];
        for(int i=0; i<count; i++)
            array[i] =  generate(field, min, max);

        return array;
    }
}
