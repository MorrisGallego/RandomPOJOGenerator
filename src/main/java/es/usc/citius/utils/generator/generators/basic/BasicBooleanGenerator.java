package es.usc.citius.utils.generator.generators.basic;

import es.usc.citius.utils.generator.generators.BooleanGenerator;

import java.util.concurrent.ThreadLocalRandom;

public class BasicBooleanGenerator implements BooleanGenerator{
    public boolean generate(){
        ThreadLocalRandom rnd = ThreadLocalRandom.current();
        return rnd.nextBoolean();
    }


}
