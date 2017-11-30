package es.usc.citius.utils.generator.generators;

import java.util.List;

public interface IntegerGenerator {
    int generate(int min, int max);
    List<Integer> generate(int min, int max, int count);
}
