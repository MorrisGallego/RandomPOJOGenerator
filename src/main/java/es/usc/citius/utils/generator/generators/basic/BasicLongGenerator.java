package es.usc.citius.utils.generator.generators.basic;

import es.usc.citius.utils.generator.generators.LongGenerator;

import java.util.concurrent.ThreadLocalRandom;

public class BasicLongGenerator implements LongGenerator{
    public long generate(long min, long max){
        ThreadLocalRandom rnd = ThreadLocalRandom.current();

        return rnd.nextLong(min, max+1);
    }
}
