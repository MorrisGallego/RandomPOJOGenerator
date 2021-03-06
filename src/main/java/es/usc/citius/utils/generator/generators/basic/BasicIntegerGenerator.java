package es.usc.citius.utils.generator.generators.basic;

import es.usc.citius.utils.generator.generators.IntegerGenerator;

import java.lang.reflect.Field;
import java.util.concurrent.ThreadLocalRandom;

public class BasicIntegerGenerator implements IntegerGenerator {
    public int generate(Field field, int min, int max) {
        ThreadLocalRandom rnd = ThreadLocalRandom.current();

        return rnd.nextInt(min, max);
    }
}
