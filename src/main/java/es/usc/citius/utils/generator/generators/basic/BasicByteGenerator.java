package es.usc.citius.utils.generator.generators.basic;

import es.usc.citius.utils.generator.generators.ByteGenerator;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BasicByteGenerator implements ByteGenerator {
    public byte generate(byte min, byte max){
        ThreadLocalRandom rnd = ThreadLocalRandom.current();

        return (byte)rnd.nextInt(min, max+1);
    }

    public List<Byte> generate(byte min, byte max, int count){
        return Stream.generate(() -> generate(min, max)).limit(count).collect(Collectors.toList());
    }

    public byte[] generateArray(byte min, byte max, int count){
        byte[] array = new byte[count];
        for(int i=0; i<count; i++)
            array[i] =  generate(min, max);

        return array;
    }
}
