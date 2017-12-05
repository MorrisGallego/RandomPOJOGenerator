package es.usc.citius.utils.generator.generators;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface BooleanGenerator {
    boolean generate();
    default List<Boolean> generate(int count){
        return Stream.generate(this::generate).limit(count).collect(Collectors.toList());
    }
    default boolean[] generateArray(int count){
        boolean[] array = new boolean[count];
        for(int i=0; i<count; i++)
            array[i] =  generate();

        return array;
    }
}
