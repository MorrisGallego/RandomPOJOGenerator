package es.usc.citius.utils.generator.generators.basic;

import es.usc.citius.utils.generator.generators.DoubleGenerator;

import java.util.concurrent.ThreadLocalRandom;

public class BasicDoubleGenerator implements DoubleGenerator{
    public double generate(double min, double max){
        ThreadLocalRandom rnd = ThreadLocalRandom.current();
        return rnd.nextDouble(min, max+1);
    }
}
