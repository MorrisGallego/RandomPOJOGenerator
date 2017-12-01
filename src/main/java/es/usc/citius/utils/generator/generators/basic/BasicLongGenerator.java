package es.usc.citius.utils.generator.generators.basic;

import es.usc.citius.utils.generator.generators.LongGenerator;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BasicLongGenerator implements LongGenerator{
    public long generate(long min, long max){
        ThreadLocalRandom rnd = ThreadLocalRandom.current();

        return rnd.nextLong(min, max+1);
    }

    public List<Long> generate(long min, long max, int count){
        return Stream.generate(() -> generate(min, max)).limit(count).collect(Collectors.toList());
    }

    public long[] generateArray(long min, long max, int count){
        long[] array = new long[count];
        for(int i=0; i<count; i++)
            array[i] =  generate(min, max);

        return array;
    }
}
