package es.usc.citius.utils.testing.generator;

import es.usc.citius.utils.generator.generators.StringGenerator;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CustomStringGenerator implements StringGenerator{

    @Override
    public String generate(int length, int minLength, int maxLength) {
        return "Hello";
    }

    @Override
    public List<String> generate(int length, int minLength, int maxLength, int count) {
        return Stream.generate(() -> this.generate(length, minLength, maxLength)).limit(count).collect(Collectors.toList());
    }
}
