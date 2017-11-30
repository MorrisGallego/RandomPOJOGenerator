package es.usc.citius.utils.generator.generators;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class FloatGenerator {
    public static float generate(float min, float max){
        ThreadLocalRandom rnd = ThreadLocalRandom.current();

        return Double.valueOf(rnd.nextDouble(Double.valueOf(Float.valueOf(min).toString()), Double.valueOf(Float.valueOf(max+1).toString()))).floatValue();
    }

    public static List<Float> generate(float min, float max, int count){
        List<Float> values = new ArrayList<>();

        for(int i = 0; i<count; i++){
            values.add(generate(min, max));
        }

        return values;
    }
}
