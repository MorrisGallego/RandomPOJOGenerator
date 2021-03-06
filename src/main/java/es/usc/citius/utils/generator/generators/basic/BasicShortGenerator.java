package es.usc.citius.utils.generator.generators.basic;

import es.usc.citius.utils.generator.generators.ShortGenerator;

import java.lang.reflect.Field;
import java.util.concurrent.ThreadLocalRandom;

public class BasicShortGenerator implements ShortGenerator {
    public short generate(Field field, short min, short max){
        ThreadLocalRandom rnd = ThreadLocalRandom.current();

        return (short)rnd.nextInt(min, max);
    }
}
