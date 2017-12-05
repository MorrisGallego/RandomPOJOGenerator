package es.usc.citius.utils.generator.generators;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface ByteGenerator {
    byte generate(byte min, byte max);
    default List<Byte> generate(byte min, byte max, int count){
        return Stream.generate(() -> generate(min, max)).limit(count).collect(Collectors.toList());
    }

    default byte[] generateArray(byte min, byte max, int count){
        byte[] array = new byte[count];
        for(int i=0; i<count; i++)
            array[i] =  generate(min, max);

        return array;
    }
}
