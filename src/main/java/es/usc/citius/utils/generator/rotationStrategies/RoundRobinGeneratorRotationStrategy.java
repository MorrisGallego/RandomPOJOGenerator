package es.usc.citius.utils.generator.rotationStrategies;

import es.usc.citius.utils.generator.Generator;

import java.util.List;

public class RoundRobinGeneratorRotationStrategy implements GeneratorRotationStrategy {
    private int lastIndex = 0;
    private static GeneratorRotationStrategy instance = null;

    private RoundRobinGeneratorRotationStrategy(){}
    public static GeneratorRotationStrategy getInstance(){
        if(instance == null)
            instance = new RoundRobinGeneratorRotationStrategy();

        return instance;
    }

    @Override
    public Generator getNextGenerator(List<Generator> generators) {
        Generator g = generators.get(lastIndex);
        this.lastIndex = (this.lastIndex + 1) % generators.size();
        return g;
    }
}
