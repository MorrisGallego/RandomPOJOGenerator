package es.usc.citius.utils.generator.generators;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class IntegerGenerator {
    public static int generate(int min, int max){
        ThreadLocalRandom rnd = ThreadLocalRandom.current();

        return rnd.nextInt(min, max+1);
    }

    public static List<Integer> generate(int min, int max, int count){
        List<Integer> values = new ArrayList<>();

        for(int i = 0; i<count; i++){
            values.add(generate(min, max));
        }

        return values;
    }
}
