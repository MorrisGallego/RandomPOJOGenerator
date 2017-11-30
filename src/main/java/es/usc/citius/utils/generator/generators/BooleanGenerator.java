package es.usc.citius.utils.generator.generators;

import java.util.List;

public interface BooleanGenerator {
    boolean generate();
    List<Boolean> generate(int count);
}
