package es.usc.citius.utils.generator.generators.basic;

import es.usc.citius.utils.generator.generators.FloatGenerator;

import java.util.concurrent.ThreadLocalRandom;

public class BasicFloatGenerator implements FloatGenerator{
    public float generate(float min, float max){
        ThreadLocalRandom rnd = ThreadLocalRandom.current();

        return Double.valueOf(rnd.nextDouble(Double.valueOf(Float.valueOf(min).toString()), Double.valueOf(Float.valueOf(max+1).toString()))).floatValue();
    }
}
