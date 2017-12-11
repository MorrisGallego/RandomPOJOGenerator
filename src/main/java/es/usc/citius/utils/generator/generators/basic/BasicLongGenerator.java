package es.usc.citius.utils.generator.generators.basic;

import es.usc.citius.utils.generator.generators.LongGenerator;

import java.lang.reflect.Field;
import java.util.concurrent.ThreadLocalRandom;

public class BasicLongGenerator implements LongGenerator{
    public long generate(Field field, long min, long max){
        ThreadLocalRandom rnd = ThreadLocalRandom.current();

        return rnd.nextLong(min, Math.min(max+1, Long.MAX_VALUE));
    }
}
