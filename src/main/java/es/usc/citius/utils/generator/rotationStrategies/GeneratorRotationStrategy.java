package es.usc.citius.utils.generator.rotationStrategies;

import es.usc.citius.utils.generator.Generator;

import java.util.List;

public interface GeneratorRotationStrategy {
    Generator getNextGenerator(List<Generator> generators);
}
