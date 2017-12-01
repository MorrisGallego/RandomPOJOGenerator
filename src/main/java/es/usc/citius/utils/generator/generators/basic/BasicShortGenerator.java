package es.usc.citius.utils.generator.generators.basic;

import es.usc.citius.utils.generator.generators.ShortGenerator;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BasicShortGenerator implements ShortGenerator {
    public short generate(short min, short max){
        ThreadLocalRandom rnd = ThreadLocalRandom.current();

        return (short)rnd.nextInt(min, max+1);
    }

    public List<Short> generate(short min, short max, int count){
        return Stream.generate(() -> generate(min, max)).limit(count).collect(Collectors.toList());
    }

    public short[] generateArray(short min, short max, int count){
        short[] array = new short[count];
        for(int i=0; i<count; i++)
            array[i] =  generate(min, max);

        return array;
    }
}
