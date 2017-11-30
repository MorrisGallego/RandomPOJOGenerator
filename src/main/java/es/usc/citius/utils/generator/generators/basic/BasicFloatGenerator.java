package es.usc.citius.utils.generator.generators.basic;

import es.usc.citius.utils.generator.generators.FloatGenerator;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BasicFloatGenerator implements FloatGenerator{
    public float generate(float min, float max){
        ThreadLocalRandom rnd = ThreadLocalRandom.current();

        return Double.valueOf(rnd.nextDouble(Double.valueOf(Float.valueOf(min).toString()), Double.valueOf(Float.valueOf(max+1).toString()))).floatValue();
    }

    public List<Float> generate(float min, float max, int count){
        return Stream.generate(() -> generate(min, max)).limit(count).collect(Collectors.toList());
    }
}
