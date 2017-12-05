package es.usc.citius.utils.generator.generators;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface StringGenerator {
    String generate(int length, int minLength, int maxLength);
    default List<String> generate(int length, int minLength, int maxLength, int count) {
        return Stream.generate(() -> this.generate(length, minLength, maxLength)).limit(count).collect(Collectors.toList());
    }
}
