package es.usc.citius.utils.generator.generators;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface CharacterGenerator {
    char generate();
    default List<Character> generate(int count){
        return Stream.generate(this::generate).limit(count).collect(Collectors.toList());
    }
    default char[] generateArray(int count){
        char[] array = new char[count];
        for(int i=0; i<count; i++)
            array[i] =  generate();

        return array;
    }
}
