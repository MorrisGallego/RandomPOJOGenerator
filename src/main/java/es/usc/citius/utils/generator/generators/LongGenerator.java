package es.usc.citius.utils.generator.generators;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class LongGenerator {
    public static long generate(long min, long max){
        ThreadLocalRandom rnd = ThreadLocalRandom.current();

        return rnd.nextLong(min, max+1);
    }

    public static List<Long> generate(long min, long max, int count){
        List<Long> values = new ArrayList<>();

        for(int i = 0; i<count; i++){
            values.add(generate(min, max));
        }

        return values;
    }
}
