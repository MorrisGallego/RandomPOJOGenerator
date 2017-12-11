package es.usc.citius.utils.generator.generators;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface StringGenerator {
    String generate(Field field, int length, int minLength, int maxLength);
    default List<String> generate(Field field, int length, int minLength, int maxLength, int count) {
        return Stream.generate(() -> this.generate(field, length, minLength, maxLength)).limit(count).collect(Collectors.toList());
    }
    default String[] generateArray(Field field, int length, int minLength, int maxLength, int count) {
        return this.generate(field, length, minLength, maxLength, count).toArray(new String[0]);
    }
}
