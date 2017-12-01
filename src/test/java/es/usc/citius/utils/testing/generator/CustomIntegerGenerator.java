package es.usc.citius.utils.testing.generator;

import es.usc.citius.utils.generator.generators.IntegerGenerator;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CustomIntegerGenerator implements IntegerGenerator{
    @Override
    public int generate(int min, int max) {
        return -1;
    }

    @Override
    public List<Integer> generate(int min, int max, int count) {
        return Stream.generate(() -> -1).collect(Collectors.toList());
    }

    @Override
    public int[] generateArray(int min, int max, int count) {
        return new int[0];
    }
}
