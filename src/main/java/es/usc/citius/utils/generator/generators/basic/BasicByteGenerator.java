package es.usc.citius.utils.generator.generators.basic;

import es.usc.citius.utils.generator.generators.ByteGenerator;

import java.lang.reflect.Field;
import java.util.concurrent.ThreadLocalRandom;

public class BasicByteGenerator implements ByteGenerator {
    public byte generate(Field field, byte min, byte max){
        ThreadLocalRandom rnd = ThreadLocalRandom.current();

        return (byte)rnd.nextInt(min, max+1);
    }
}
