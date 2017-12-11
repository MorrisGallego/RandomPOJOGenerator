package es.usc.citius.utils.generator.generators;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface FloatGenerator {
    float generate(Field field, float min, float max);
    default List<Float> generate(Field field, float min, float max, int count){
        return Stream.generate(() -> generate(field, min, max)).limit(count).collect(Collectors.toList());
    }
    default float[] generateArray(Field field, float min, float max, int count){
        float[] array = new float[count];
        for(int i=0; i<count; i++)
            array[i] =  generate(field, min, max);

        return array;
    }
}
