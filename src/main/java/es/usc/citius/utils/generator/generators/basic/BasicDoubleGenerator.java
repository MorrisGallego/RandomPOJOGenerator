package es.usc.citius.utils.generator.generators.basic;

import es.usc.citius.utils.generator.generators.DoubleGenerator;

import java.lang.reflect.Field;
import java.util.concurrent.ThreadLocalRandom;

public class BasicDoubleGenerator implements DoubleGenerator{
    public double generate(Field field, double min, double max){
        ThreadLocalRandom rnd = ThreadLocalRandom.current();
        return rnd.nextDouble(min, Math.min(max+1, Double.MAX_VALUE));
    }
}
