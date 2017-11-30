package es.usc.citius.utils.generator.generators;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class BooleanGenerator {
    public static boolean generate(){
        ThreadLocalRandom rnd = ThreadLocalRandom.current();

        return rnd.nextBoolean();
    }

    public static List<Boolean> generate(int count){
        List<Boolean> values = new ArrayList<>();

        for(int i = 0; i<count; i++){
            values.add(generate());
        }

        return values;
    }
}
