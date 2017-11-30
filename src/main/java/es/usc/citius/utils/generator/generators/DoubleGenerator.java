package es.usc.citius.utils.generator.generators;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class DoubleGenerator {
    public static double generate(double min, double max){
        ThreadLocalRandom rnd = ThreadLocalRandom.current();

        return rnd.nextDouble(min, max+1);
    }

    public static List<Double> generate(double min, double max, int count){
        List<Double> values = new ArrayList<>();

        for(int i = 0; i<count; i++){
            values.add(generate(min, max));
        }

        return values;
    }
}
