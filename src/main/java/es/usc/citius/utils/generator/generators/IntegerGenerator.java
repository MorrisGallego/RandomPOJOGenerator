package es.usc.citius.utils.generator.generators;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface IntegerGenerator {
    int generate(int min, int max);
    default List<Integer> generate(int min, int max, int count){
        return Stream.generate(() -> generate(min, max)).limit(count).collect(Collectors.toList());
    }
    default int[] generateArray(int min, int max, int count){
        int[] array = new int[count];
        for(int i=0; i<count; i++)
            array[i] =  generate(min, max);

        return array;
    }
}
