package es.usc.citius.utils.generator.generators.basic;

import es.usc.citius.utils.generator.generators.IntegerGenerator;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BasicIntegerGenerator implements IntegerGenerator{
    public int generate(int min, int max){
        ThreadLocalRandom rnd = ThreadLocalRandom.current();

        return rnd.nextInt(min, max+1);
    }

    public List<Integer> generate(int min, int max, int count){
        return Stream.generate(() -> generate(min, max)).limit(count).collect(Collectors.toList());
    }
}
