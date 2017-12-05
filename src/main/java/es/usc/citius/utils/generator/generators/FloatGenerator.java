package es.usc.citius.utils.generator.generators;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface FloatGenerator {
    float generate(float min, float max);
    default List<Float> generate(float min, float max, int count){
        return Stream.generate(() -> generate(min, max)).limit(count).collect(Collectors.toList());
    }

    default float[] generateArray(float min, float max, int count){
        float[] array = new float[count];
        for(int i=0; i<count; i++)
            array[i] =  generate(min, max);

        return array;
    }
}
