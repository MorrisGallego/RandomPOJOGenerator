package es.usc.citius.utils.generator.generators;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface ShortGenerator {
    short generate(short min, short max);
    default List<Short> generate(short min, short max, int count){
        return Stream.generate(() -> generate(min, max)).limit(count).collect(Collectors.toList());
    }
    default short[] generateArray(short min, short max, int count){
        short[] array = new short[count];
        for(int i=0; i<count; i++)
            array[i] =  generate(min, max);

        return array;
    }
}
