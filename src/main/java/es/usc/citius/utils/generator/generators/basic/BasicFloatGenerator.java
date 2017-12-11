package es.usc.citius.utils.generator.generators.basic;

import es.usc.citius.utils.generator.generators.FloatGenerator;

import java.lang.reflect.Field;
import java.util.concurrent.ThreadLocalRandom;

public class BasicFloatGenerator implements FloatGenerator{
    public float generate(Field field, float min, float max){
        ThreadLocalRandom rnd = ThreadLocalRandom.current();

        return Double.valueOf(
                rnd.nextDouble(
                        Double.valueOf(Float.valueOf(min).toString()),
                        Double.valueOf(Float.valueOf(Math.min(max+1, Float.MAX_VALUE)).toString())))
                        .floatValue();
    }
}
