package es.usc.citius.utils.generator.rotationStrategies;

import es.usc.citius.utils.generator.Generator;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class RandomGeneratorRotationStrategy implements GeneratorRotationStrategy {
    private static GeneratorRotationStrategy instance = null;

    private RandomGeneratorRotationStrategy(){}
    public static GeneratorRotationStrategy getInstance(){
        if(instance == null)
            instance = new RandomGeneratorRotationStrategy();

        return instance;
    }

    @Override
    public Generator getNextGenerator(List<Generator> generators) {
        ThreadLocalRandom rnd = ThreadLocalRandom.current();
        return generators.get(rnd.nextInt(0, generators.size()));
    }
}
