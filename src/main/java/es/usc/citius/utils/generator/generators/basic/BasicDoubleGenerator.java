package es.usc.citius.utils.generator.generators.basic;

import es.usc.citius.utils.generator.generators.DoubleGenerator;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BasicDoubleGenerator implements DoubleGenerator{
    public double generate(double min, double max){
        ThreadLocalRandom rnd = ThreadLocalRandom.current();
        return rnd.nextDouble(min, max+1);
    }

    public List<Double> generate(double min, double max, int count){
        return Stream.generate(() -> generate(min, max)).limit(count).collect(Collectors.toList());
    }
}
