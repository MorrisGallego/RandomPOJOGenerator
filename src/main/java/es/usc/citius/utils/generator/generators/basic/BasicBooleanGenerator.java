package es.usc.citius.utils.generator.generators.basic;

import es.usc.citius.utils.generator.generators.BooleanGenerator;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BasicBooleanGenerator implements BooleanGenerator{
    public boolean generate(){
        ThreadLocalRandom rnd = ThreadLocalRandom.current();
        return rnd.nextBoolean();
    }

    public List<Boolean> generate(int count){
        return Stream.generate(this::generate).limit(count).collect(Collectors.toList());
    }

    public boolean[] generateArray(int count){
        boolean[] array = new boolean[count];
        for(int i=0; i<count; i++)
            array[i] =  generate();

        return array;
    }
}
